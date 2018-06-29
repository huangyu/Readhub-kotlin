package com.huangyu.readhub.ui.dev.adapter

import android.content.Context
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.huangyu.readhub.R
import com.huangyu.readhub.base.BaseAdapter
import com.huangyu.readhub.base.BaseViewHolder
import com.huangyu.readhub.data.bean.TechNew
import com.huangyu.readhub.ui.article.ArticleActivity
import com.huangyu.readhub.ui.main.MainActivity
import org.joda.time.DateTime
import org.ocpsoft.prettytime.PrettyTime

class DevNewsAdapter(context: Context?) : BaseAdapter<TechNew>(context) {

    private val prettyTime = PrettyTime()

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): BaseViewHolder<TechNew?> {
        return TechNewsViewHolder(inflater.inflate(R.layout.item_tech_news, parent, false))
    }

    inner class TechNewsViewHolder(itemView: View) : BaseViewHolder<TechNew?>(itemView) {

        private lateinit var llRoot: LinearLayout
        private lateinit var tvTitle: TextView
        private lateinit var tvAuthor: TextView
        private lateinit var tvTime: TextView

        override fun onBind(t: TechNew?, position: Int) {
            llRoot = findViewById(R.id.ll_root)

            tvTitle = findViewById(R.id.tv_title)
            tvAuthor = findViewById(R.id.tv_author)
            tvTime = findViewById(R.id.tv_time)

            tvTitle.text = t?.title?.trim()
            val a = if (t?.authorName == null) "" else t.authorName
            val author = if (a.isEmpty()) t?.siteName else t?.siteName + " / " + a
            tvAuthor.text = author
            tvTime.text = getFormatTime(t, prettyTime)

            llRoot.setOnClickListener{ _ ->
                ArticleActivity.start(context as MainActivity, t!!.title, t.mobileUrl)
            }
        }

        private fun getFormatTime(t: TechNew?, prettyTime: PrettyTime): SpannableString {
            val time = prettyTime.format(DateTime(t!!.publishDate).toDate()).replace(" ", "")
            val formatTime = SpannableString(time)
            formatTime.setSpan(ForegroundColorSpan(Color.parseColor("#9E9E9E")), 0, time.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            formatTime.setSpan(AbsoluteSizeSpan(14, true), 0, time.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            return formatTime
        }

    }

}
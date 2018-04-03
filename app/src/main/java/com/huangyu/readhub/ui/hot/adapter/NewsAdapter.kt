package com.huangyu.readhub.ui.hot.adapter

import android.content.Context
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.huangyu.readhub.R
import com.huangyu.readhub.base.BaseAdapter
import com.huangyu.readhub.base.BaseViewHolder
import com.huangyu.readhub.data.bean.News

/**
 * Created by huangyu on 2018/3/30.
 */
class NewsAdapter(context: Context?) : BaseAdapter<News>(context) {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): BaseViewHolder<News?> {
        return NewsAdapter.NewsHolder(inflater.inflate(R.layout.item_news, parent, false))
    }

    class NewsHolder(itemView: View) : BaseViewHolder<News?>(itemView) {

        private lateinit var tvTitle: TextView
        private lateinit var tvSite: TextView

        override fun onBind(t: News?, position: Int) {

            tvTitle = findViewById(R.id.tv_title)
            tvSite = findViewById(R.id.tv_site)

            val title = t?.title?.trim()
            val site = t?.siteName?.trim()
            val formatTitle = SpannableString(title)
            formatTitle.setSpan(UnderlineSpan(), 0, title!!.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            formatTitle.setSpan(MyClickableSpan(t.mobileUrl), 0, title!!.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            tvTitle.text = formatTitle
            tvSite.text = site
        }

    }

    class MyClickableSpan(val url: String) : ClickableSpan() {

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.color = Color.BLUE
            ds.isUnderlineText = true
        }

        override fun onClick(widget: View?) {
            // TODO
        }

    }

}
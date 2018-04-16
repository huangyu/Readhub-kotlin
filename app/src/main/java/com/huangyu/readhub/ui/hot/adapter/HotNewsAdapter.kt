package com.huangyu.readhub.ui.hot.adapter

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
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
import com.huangyu.readhub.ui.article.ArticleActivity
import com.huangyu.readhub.ui.main.MainActivity

/**
 * Created by huangyu on 2018/3/30.
 */
class HotNewsAdapter(context: Context?) : BaseAdapter<News>(context) {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): BaseViewHolder<News?> {
        return HotNewsAdapter.NewsHolder(context, inflater.inflate(R.layout.item_news, parent, false))
    }

    class NewsHolder(val context: Context?, itemView: View) : BaseViewHolder<News?>(itemView) {

        private lateinit var tvTitle: TextView
        private lateinit var tvSite: TextView

        override fun onBind(t: News?, position: Int) {

            tvTitle = findViewById(R.id.tv_title)
            tvSite = findViewById(R.id.tv_site)

            tvTitle.movementMethod = LinkMovementMethod.getInstance()

            val title = t!!.title
            val site = t.siteName
            val formatTitle = SpannableString(title)
            formatTitle.setSpan(UnderlineSpan(), 0, title.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            formatTitle.setSpan(DefaultClickableSpan(context, t.title, t.mobileUrl), 0, title.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            tvTitle.text = formatTitle
            tvSite.text = site
        }

    }

    class DefaultClickableSpan(private val context: Context?, private val title: String, private val url: String) : ClickableSpan() {

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.color = context!!.resources.getColor(R.color.colorPrimary)
            ds.isUnderlineText = true
        }

        override fun onClick(widget: View?) {
            val tvTitle = widget as TextView

            val formatTitle = SpannableString(title)
            formatTitle.setSpan(UnderlineSpan(), 0, title.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            formatTitle.setSpan(CheckedClickableSpan(context, title, url), 0, title.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            tvTitle.text = formatTitle

            ArticleActivity.start(context as MainActivity, title, url)
        }

    }

    class CheckedClickableSpan(private val context: Context?, private val title: String, private val url: String) : ClickableSpan() {

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.color = context!!.resources.getColor(R.color.colorTxtSub)
            ds.isUnderlineText = true
        }

        override fun onClick(widget: View?) {
            ArticleActivity.start(context as MainActivity, title, url)
        }

    }

}
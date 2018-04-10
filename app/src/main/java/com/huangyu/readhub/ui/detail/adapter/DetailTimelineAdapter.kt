package com.huangyu.readhub.ui.detail.adapter

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
import com.huangyu.readhub.data.bean.TopicSummary
import com.huangyu.readhub.ui.detail.DetailActivity
import org.joda.time.DateTime
import org.ocpsoft.prettytime.PrettyTime

/**
 * Created by huangyu on 2018/4/10.
 */
class DetailTimelineAdapter(context: Context?) : BaseAdapter<TopicSummary>(context) {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): BaseViewHolder<TopicSummary?> {
        return DetailTimelineAdapter.NewsHolder(context, inflater.inflate(R.layout.item_timeline, parent, false))
    }

    class NewsHolder(val context: Context?, itemView: View) : BaseViewHolder<TopicSummary?>(itemView) {

        private lateinit var tvTitle: TextView
        private lateinit var tvTime: TextView

        private val prettyTime = PrettyTime()

        override fun onBind(t: TopicSummary?, position: Int) {
            tvTitle = findViewById(R.id.tv_title)
            tvTime = findViewById(R.id.tv_time)

            tvTitle.movementMethod = LinkMovementMethod.getInstance()

            val title = t!!.title.trim()
            val formatTitle = SpannableString(title)
            formatTitle.setSpan(UnderlineSpan(), 0, title.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            formatTitle.setSpan(DefaultClickableSpan(context, t.title.trim(), t.id), 0, title.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            tvTitle.text = formatTitle

            tvTime.text = prettyTime.format(DateTime(t.createdAt).toDate()).replace(" ", "")
        }

    }

    class DefaultClickableSpan(private val context: Context?, private val title: String, private val id: String) : ClickableSpan() {

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.color = context!!.resources.getColor(R.color.colorPrimary)
            ds.isUnderlineText = true
        }

        override fun onClick(widget: View?) {
            val tvTitle = widget as TextView

            val formatTitle = SpannableString(title)
            formatTitle.setSpan(UnderlineSpan(), 0, title.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            formatTitle.setSpan(CheckedClickableSpan(context, id, title), 0, title.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            tvTitle.text = formatTitle

            DetailActivity.start(context as DetailActivity, id, title)
        }

    }

    class CheckedClickableSpan(private val context: Context?, private val id: String, private val title: String) : ClickableSpan() {

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.color = context!!.resources.getColor(R.color.colorTxtSub)
            ds.isUnderlineText = true
        }

        override fun onClick(widget: View?) {
            DetailActivity.start(context as DetailActivity, id, title)
        }

    }

}
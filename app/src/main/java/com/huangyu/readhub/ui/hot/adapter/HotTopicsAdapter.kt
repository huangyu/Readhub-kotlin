package com.huangyu.readhub.ui.hot.adapter

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
import com.huangyu.readhub.custom.ExpandableLayout
import com.huangyu.readhub.data.bean.Topic
import org.joda.time.DateTime
import org.ocpsoft.prettytime.PrettyTime


/**
 * Created by huangyu on 2018/3/28.
 */
class HotTopicsAdapter(context: Context?) : BaseAdapter<Topic>(context) {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): BaseViewHolder<Topic?> {
        return HotTopicsViewHolder(inflater.inflate(R.layout.item_hot_topics, parent, false))
    }

    class HotTopicsViewHolder(itemView: View) : BaseViewHolder<Topic?>(itemView) {

        private lateinit var tvTitle: TextView
        private lateinit var tvSummary: TextView
        private lateinit var expandableLayout: ExpandableLayout
        private lateinit var llClick: LinearLayout

        private val prettyTime = PrettyTime()

        override fun onBind(t: Topic?, position: Int) {
            tvTitle = findViewById(R.id.tv_title)
            tvSummary = findViewById(R.id.tv_summary)
            expandableLayout = findViewById(R.id.expandable_layout)
            llClick = findViewById(R.id.fl_click)

            var title = t?.title?.trim()
            val summary = t?.summary?.trim()
            val time = prettyTime.format(DateTime(t?.publishDate).toDate()).replace(" ", "")
            title = """$title    $time"""

            val formatTitle = SpannableString(title)
            formatTitle.setSpan(ForegroundColorSpan(Color.parseColor("#9E9E9E")), title.lastIndexOf(" "), title.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            formatTitle.setSpan(AbsoluteSizeSpan(12, true), title.lastIndexOf(" "), title.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            tvTitle.text = formatTitle
            tvSummary.text = summary

            llClick.setOnClickListener({ _ ->
                expandableLayout.toggle()
            })
        }

    }


}
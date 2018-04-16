package com.huangyu.readhub.ui.hot.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.huangyu.readhub.R
import com.huangyu.readhub.base.BaseAdapter
import com.huangyu.readhub.base.BaseViewHolder
import com.huangyu.readhub.custom.ExpandableLayout
import com.huangyu.readhub.custom.ExpandableLayout.State.Companion.COLLAPSED
import com.huangyu.readhub.custom.ExpandableLayout.State.Companion.COLLAPSING
import com.huangyu.readhub.custom.ExpandableLayout.State.Companion.EXPANDED
import com.huangyu.readhub.custom.ExpandableLayout.State.Companion.EXPANDING
import com.huangyu.readhub.data.bean.Topic
import com.huangyu.readhub.ui.detail.DetailActivity
import com.huangyu.readhub.ui.main.MainActivity
import org.joda.time.DateTime
import org.ocpsoft.prettytime.PrettyTime

/**
 * Created by huangyu on 2018/3/28.
 */
class HotTopicsAdapter(context: Context?) : BaseAdapter<Topic>(context) {

    private var expandStateArray: SparseArray<Boolean> = SparseArray()
    private val prettyTime = PrettyTime()
    private lateinit var adapter: HotNewsAdapter

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): BaseViewHolder<Topic?> {
        return HotTopicsViewHolder(inflater.inflate(R.layout.item_hot_topics, parent, false))
    }

    inner class HotTopicsViewHolder(itemView: View) : BaseViewHolder<Topic?>(itemView) {

        private lateinit var llRoot: LinearLayout
        private lateinit var tvTitle: TextView
        private lateinit var tvTime: TextView
        private lateinit var tvSummary: TextView
        private lateinit var expandableLayout: ExpandableLayout
        private lateinit var rvNews: RecyclerView

        override fun onBind(t: Topic?, position: Int) {
            llRoot = findViewById(R.id.ll_root)

            tvTitle = findViewById(R.id.tv_title)
            tvTime = findViewById(R.id.tv_time)
            tvSummary = findViewById(R.id.tv_summary)
            expandableLayout = findViewById(R.id.expandable_layout)
            expandableLayout.setOnExpansionUpdateListener(object : ExpandableLayout.OnExpansionUpdateListener {
                override fun onExpansionUpdate(expansionFraction: Float, state: Int) {
                    when (state) {
                        COLLAPSING, COLLAPSED -> {
                            llRoot.setBackgroundResource(R.color.colorBg)
                            expandStateArray.put(position, false)
                        }
                        EXPANDING, EXPANDED -> {
                            llRoot.setBackgroundResource(R.drawable.card_bg)
                            expandStateArray.put(position, true)
                        }
                    }
                }
            })
            expandableLayout.setExpanded(expandStateArray.get(position, false), false)

            rvNews = findViewById(R.id.rv_news)

            tvTitle.text = t!!.title
            tvTime.text = getFormatTime(t, prettyTime)
            tvSummary.text = t.summary

            llRoot.setOnClickListener({ _ ->
                expandableLayout.toggle()
            })

            tvSummary.setOnClickListener({ _ ->
                DetailActivity.start(context as MainActivity, t.id, t.title)
            })

            rvNews.layoutManager = LinearLayoutManager(context)
            if (rvNews.adapter == null) {
                adapter = HotNewsAdapter(context)
                rvNews.adapter = adapter
            }
            adapter.initList(t.newsArray.take(3))
        }

        private fun getFormatTime(t: Topic?, prettyTime: PrettyTime): SpannableString {
            val time = prettyTime.format(DateTime(t!!.publishDate).toDate()).replace(" ", "")
            val formatTime = SpannableString(time)
            formatTime.setSpan(ForegroundColorSpan(Color.parseColor("#9E9E9E")), 0, time.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            formatTime.setSpan(AbsoluteSizeSpan(14, true), 0, time.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            return formatTime
        }

    }

}
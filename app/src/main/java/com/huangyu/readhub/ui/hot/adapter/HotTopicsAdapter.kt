package com.huangyu.readhub.ui.hot.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): BaseViewHolder<Topic?> {
        return HotTopicsViewHolder(context, inflater.inflate(R.layout.item_hot_topics, parent, false))
    }

    class HotTopicsViewHolder(val context: Context?, itemView: View) : BaseViewHolder<Topic?>(itemView) {

        private lateinit var llRoot: LinearLayout
        private lateinit var tvTitle: TextView
        private lateinit var tvSummary: TextView
        private lateinit var expandableLayout: ExpandableLayout
        private lateinit var rvNews: RecyclerView
        private val viewPool = RecyclerView.RecycledViewPool()

        private lateinit var adapter: HotNewsAdapter
        private var manager = LinearLayoutManager(context)

        private val prettyTime = PrettyTime()

        override fun onBind(t: Topic?, position: Int) {
            llRoot = findViewById(R.id.ll_root)

            tvTitle = findViewById(R.id.tv_title)
            tvSummary = findViewById(R.id.tv_summary)
            expandableLayout = findViewById(R.id.expandable_layout)
            expandableLayout.setOnExpansionUpdateListener(object : ExpandableLayout.OnExpansionUpdateListener {
                override fun onExpansionUpdate(expansionFraction: Float, state: Int) {
                    when (state) {
                        COLLAPSING -> llRoot.setBackgroundResource(R.color.colorBg)
                        COLLAPSED -> llRoot.setBackgroundResource(R.color.colorBg)
                        EXPANDING -> llRoot.setBackgroundResource(R.drawable.card_bg)
                        EXPANDED -> llRoot.setBackgroundResource(R.drawable.card_bg)
                    }
                }

            })
            rvNews = findViewById(R.id.rv_news)

            tvTitle.text = getFormatTitle(t, prettyTime)
            tvSummary.text = t?.summary?.trim()

            tvTitle.setOnClickListener({ _ ->
                expandableLayout.toggle()
            })

            tvSummary.setOnClickListener({ _ ->
                DetailActivity.start(context as MainActivity, t!!.id, t.title)
            })

            rvNews.layoutManager = manager
            rvNews.recycledViewPool = viewPool
            if (rvNews.adapter == null) {
                adapter = HotNewsAdapter(context)
                rvNews.adapter = adapter
            }
            adapter.initList(t?.newsArray!!.take(3))
        }

        private fun getFormatTitle(t: Topic?, prettyTime: PrettyTime): SpannableString {
            var title = t?.title?.trim()
            val time = prettyTime.format(DateTime(t?.publishDate).toDate()).replace(" ", "")
            title = """$title    $time"""
            val formatTitle = SpannableString(title)
            formatTitle.setSpan(ForegroundColorSpan(Color.parseColor("#9E9E9E")), title.lastIndexOf(" "), title.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            formatTitle.setSpan(AbsoluteSizeSpan(14, true), title.lastIndexOf(" "), title.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            return formatTitle
        }

    }

}
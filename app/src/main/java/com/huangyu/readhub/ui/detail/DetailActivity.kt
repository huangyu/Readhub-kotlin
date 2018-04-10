package com.huangyu.readhub.ui.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.huangyu.readhub.R
import com.huangyu.readhub.base.BaseInjectActivity
import com.huangyu.readhub.data.bean.TopicDetail
import com.huangyu.readhub.ui.detail.adapter.DetailNewsAdapter
import com.huangyu.readhub.ui.detail.adapter.DetailTimelineAdapter
import com.huangyu.readhub.ui.detail.model.IDetailModel
import com.huangyu.readhub.ui.detail.presenter.IDetailPresenter
import com.huangyu.readhub.ui.detail.view.IDetailView
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

/**
 * Created by huangyu on 2018/4/10.
 */
class DetailActivity : BaseInjectActivity(), IDetailView {

    companion object {
        private const val EXTRA_ID = "id"
        private const val EXTRA_TITLE = "title"

        fun start(activity: Activity, id: String, title: String) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(EXTRA_ID, id)
            intent.putExtra(EXTRA_TITLE, title)
            activity.startActivity(intent)
        }
    }

    private lateinit var title: String
    private lateinit var id: String

    @Inject
    lateinit var presenter: IDetailPresenter<IDetailView, IDetailModel>

    private lateinit var detailNewsAdapter: DetailNewsAdapter
    private lateinit var detailTimelineAdapter: DetailTimelineAdapter

    override fun getLayoutId(): Int = R.layout.activity_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onAttach(this)
    }

    override fun initView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        title = intent.getStringExtra(EXTRA_TITLE)
        setTitle(title)

        rv_news.layoutManager = LinearLayoutManager(this)
        detailNewsAdapter = DetailNewsAdapter(this)
        rv_news.adapter = detailNewsAdapter

        rv_timeline.layoutManager = LinearLayoutManager(this)
        detailTimelineAdapter = DetailTimelineAdapter(this)
        rv_timeline.adapter = detailTimelineAdapter
    }

    override fun initData() {
        tv_dependent_event.visibility = View.INVISIBLE

        id = intent.getStringExtra("id")
        presenter.queryTopicDetail(id)
    }

    override fun onRefreshFinish(detail: TopicDetail) {
        tv_title.text = detail.title
        tv_summary.text = detail.summary

        tv_dependent_event.visibility = View.VISIBLE

        detailNewsAdapter.initList(detail.newsArray.take(3))
        detailTimelineAdapter.initList(detail.timeline.topics.take(5))
    }

    override fun onRefreshError(msg: Throwable) {
        println(msg)

        tv_dependent_event.visibility = View.INVISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

}
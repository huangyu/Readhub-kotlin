package com.huangyu.readhub.ui.tech

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.aspsine.irecyclerview.OnLoadMoreListener
import com.huangyu.readhub.R
import com.huangyu.readhub.base.LazyLoadFragment
import com.huangyu.readhub.custom.UniversalLoadMoreFooterView
import com.huangyu.readhub.data.bean.TechNew
import com.huangyu.readhub.ui.tech.adapter.TechNewsAdapter
import com.huangyu.readhub.ui.tech.model.ITechNewsModel
import com.huangyu.readhub.ui.tech.presenter.ITechNewsPresenter
import com.huangyu.readhub.ui.tech.view.ITechNewsView
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_hot.*
import org.joda.time.DateTime
import javax.inject.Inject

/**
 * Created by huangyu on 2018/3/28.
 */
class TechNewsFragment : LazyLoadFragment(), ITechNewsView, SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener {

    companion object {
        fun newInstance(): TechNewsFragment {
            return TechNewsFragment()
        }
    }

    @Inject
    lateinit var presenter: ITechNewsPresenter<ITechNewsView, ITechNewsModel>

    private lateinit var adapter: TechNewsAdapter

    private lateinit var footer: UniversalLoadMoreFooterView

    private var lastCursor: Long = 0

    override fun setContentView(): Int = R.layout.fragment_tech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun lazyLoad() {
        initView()
        initData()
    }

    private fun initView() {
        swipe_refresh_layout.setColorSchemeResources(R.color.colorAccent)
        swipe_refresh_layout.setOnRefreshListener(this)
        swipe_refresh_layout.setOnChildScrollUpCallback({ _, _ ->
            recycler_view.scrollY > 0
        })

        adapter = TechNewsAdapter(context)
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.iAdapter = adapter
        recycler_view.setOnLoadMoreListener(this)

        footer = recycler_view.loadMoreFooterView as UniversalLoadMoreFooterView
        footer.status = UniversalLoadMoreFooterView.Status.GONE
    }

    private fun initData() {
        footer.status = UniversalLoadMoreFooterView.Status.GONE
        swipe_refresh_layout.post { swipe_refresh_layout.isRefreshing = true }

        onRefresh()
    }

    override fun initList(list: List<TechNew>) {
        footer.status = UniversalLoadMoreFooterView.Status.GONE
        lastCursor = DateTime(list[list.lastIndex].publishDate).toDate().time

        adapter.initList(list)
        swipe_refresh_layout.isRefreshing = false
    }

    override fun appendList(list: List<TechNew>) {
        footer.status = UniversalLoadMoreFooterView.Status.GONE
        lastCursor = DateTime(list[list.lastIndex].publishDate).toDate().time

        adapter.appendList(list)
        swipe_refresh_layout.isRefreshing = false
    }

    override fun onRefresh() {
        adapter.clearList()

        presenter.queryTechNews()
    }

    override fun onLoadMore() {
        footer.status = UniversalLoadMoreFooterView.Status.LOADING

        presenter.queryTechNews(lastCursor)
    }

    override fun stopLoad() {
        swipe_refresh_layout.isRefreshing = false
        footer.status = UniversalLoadMoreFooterView.Status.GONE
    }

    override fun onRefreshError(msg: Throwable) {
        println(msg)

        swipe_refresh_layout.isRefreshing = false
        footer.status = UniversalLoadMoreFooterView.Status.GONE
    }

    override fun onLoadError(msg: Throwable) {
        println(msg)

        swipe_refresh_layout.isRefreshing = false
        footer.status = UniversalLoadMoreFooterView.Status.ERROR
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }

}
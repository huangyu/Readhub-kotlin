package com.huangyu.readhub.ui.hot.fragment

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.aspsine.irecyclerview.OnLoadMoreListener
import com.huangyu.readhub.R
import com.huangyu.readhub.base.LazyLoadFragment
import com.huangyu.readhub.custom.UniversalLoadMoreFooterView
import com.huangyu.readhub.data.bean.Topic
import com.huangyu.readhub.ui.hot.adapter.HotTopicsAdapter
import com.huangyu.readhub.ui.hot.model.IHotTopicsModel
import com.huangyu.readhub.ui.hot.presenter.IHotTopicsPresenter
import com.huangyu.readhub.ui.hot.view.IHotTopicsView
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_hot.*
import javax.inject.Inject


/**
 * Created by huangyu on 2018/3/28.
 */
class HotTopicsFragment : LazyLoadFragment(), IHotTopicsView, SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener {

    companion object {
        fun newInstance(): HotTopicsFragment {
            return HotTopicsFragment()
        }
    }

    @Inject
    lateinit var presenter: IHotTopicsPresenter<IHotTopicsView, IHotTopicsModel>

    private lateinit var adapter: HotTopicsAdapter

    private lateinit var footer: UniversalLoadMoreFooterView

    private var lastCursor: Int = 0

    override fun setContentView(): Int = R.layout.fragment_hot

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

        adapter = HotTopicsAdapter(context)
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.iAdapter = adapter
        recycler_view.setOnLoadMoreListener(this)
//        recycler_view.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        footer = recycler_view.loadMoreFooterView as UniversalLoadMoreFooterView
        footer.status = UniversalLoadMoreFooterView.Status.GONE
    }

    private fun initData() {
        footer.status = UniversalLoadMoreFooterView.Status.GONE
        swipe_refresh_layout.post { swipe_refresh_layout.isRefreshing = true }
        onRefresh()
    }

    override fun initList(list: List<Topic>) {
        footer.status = UniversalLoadMoreFooterView.Status.GONE
        lastCursor = list[list.lastIndex].order

        adapter.initList(list)
        swipe_refresh_layout.isRefreshing = false
    }

    override fun appendList(list: List<Topic>) {
        footer.status = UniversalLoadMoreFooterView.Status.GONE
        lastCursor = list[list.lastIndex].order

        adapter.appendList(list)
        swipe_refresh_layout.isRefreshing = false
    }

    override fun onRefresh() {
        presenter.queryHotTopics()
    }

    override fun onLoadMore() {
        footer.status = UniversalLoadMoreFooterView.Status.LOADING

        presenter.queryHotTopics(lastCursor)
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
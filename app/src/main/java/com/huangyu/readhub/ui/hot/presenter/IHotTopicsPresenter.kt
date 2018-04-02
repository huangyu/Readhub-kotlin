package com.huangyu.readhub.ui.hot.presenter

import com.huangyu.readhub.mvp.IPresenter
import com.huangyu.readhub.ui.hot.model.IHotTopicsModel
import com.huangyu.readhub.ui.hot.view.IHotTopicsView

/**
 * Created by huangyu on 2018/3/28.
 */
interface IHotTopicsPresenter<V : IHotTopicsView, M : IHotTopicsModel> : IPresenter<V, M> {

    fun queryHotTopics(vararg lastCursor: Int)

}
package com.huangyu.readhub.ui.dev.presenter

import com.huangyu.readhub.mvp.IPresenter
import com.huangyu.readhub.ui.dev.model.IDevNewsModel
import com.huangyu.readhub.ui.dev.view.IDevNewsView

interface IDevNewsPresenter<V : IDevNewsView, M : IDevNewsModel> : IPresenter<V, M> {

    fun queryDevNews(vararg lastCursor: Long)

}
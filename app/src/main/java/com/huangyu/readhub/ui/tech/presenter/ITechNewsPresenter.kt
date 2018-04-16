package com.huangyu.readhub.ui.tech.presenter

import com.huangyu.readhub.mvp.IPresenter
import com.huangyu.readhub.ui.tech.model.ITechNewsModel
import com.huangyu.readhub.ui.tech.view.ITechNewsView

interface ITechNewsPresenter<V : ITechNewsView, M : ITechNewsModel> : IPresenter<V, M> {

    fun queryTechNews(vararg lastCursor: Long)

}
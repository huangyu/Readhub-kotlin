package com.huangyu.readhub.ui.dev.view

import com.huangyu.readhub.data.bean.TechNew
import com.huangyu.readhub.mvp.IView

interface IDevNewsView : IView {

    fun initList(list: List<TechNew>)

    fun appendList(list: List<TechNew>)

    fun onRefreshError(msg: Throwable)

    fun onLoadError(msg: Throwable)

}
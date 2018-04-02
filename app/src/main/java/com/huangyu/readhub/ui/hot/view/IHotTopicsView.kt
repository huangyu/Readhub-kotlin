package com.huangyu.readhub.ui.hot.view

import com.huangyu.readhub.data.bean.Topic
import com.huangyu.readhub.mvp.IView

interface IHotTopicsView : IView {

    fun initList(list: List<Topic>)

    fun appendList(list: List<Topic>)

    fun onRefreshError(msg: Throwable)

    fun onLoadError(msg: Throwable)

}
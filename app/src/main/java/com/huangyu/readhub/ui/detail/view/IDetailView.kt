package com.huangyu.readhub.ui.detail.view

import com.huangyu.readhub.data.bean.TopicDetail
import com.huangyu.readhub.mvp.IView

interface IDetailView : IView {

    fun onRefreshFinish(detail: TopicDetail)

    fun onRefreshError(msg: Throwable)

}
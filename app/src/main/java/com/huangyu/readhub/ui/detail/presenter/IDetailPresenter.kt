package com.huangyu.readhub.ui.detail.presenter

import com.huangyu.readhub.mvp.IPresenter
import com.huangyu.readhub.ui.detail.model.IDetailModel
import com.huangyu.readhub.ui.detail.view.IDetailView

/**
 * Created by huangyu on 2018/4/10.
 */
interface IDetailPresenter<V : IDetailView, M : IDetailModel> : IPresenter<V, M> {

    fun queryTopicDetail(topicId: String)

}
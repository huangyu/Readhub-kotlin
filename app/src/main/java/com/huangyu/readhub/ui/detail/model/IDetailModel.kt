package com.huangyu.readhub.ui.detail.model

import com.huangyu.readhub.data.bean.TopicDetail
import com.huangyu.readhub.mvp.IModel
import io.reactivex.Observable

interface IDetailModel : IModel {

    fun queryDetail(id: String): Observable<TopicDetail>

}
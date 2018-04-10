package com.huangyu.readhub.ui.detail.model

import com.huangyu.readhub.data.bean.TopicDetail
import com.huangyu.readhub.data.net.ApiService
import com.huangyu.readhub.mvp.BaseModel
import io.reactivex.Observable
import javax.inject.Inject

class DetailModel @Inject internal constructor(private val service: ApiService) : BaseModel(), IDetailModel {

    override fun queryDetail(id: String): Observable<TopicDetail> {
        return service.getTopicDetail(id)
    }

}
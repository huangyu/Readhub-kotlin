package com.huangyu.readhub.ui.hot.model

import com.huangyu.readhub.data.bean.Topics
import com.huangyu.readhub.data.net.ApiService
import com.huangyu.readhub.mvp.BaseModel
import com.huangyu.readhub.util.AppConstants
import io.reactivex.Observable
import javax.inject.Inject

class HotTopicsModel @Inject internal constructor(private val service: ApiService) : BaseModel(), IHotTopicsModel {

    override fun queryHotTopics(): Observable<Topics> {
        return service.getTopics(AppConstants.PER_PAGE_SIZE)
    }

    override fun queryHotTopics(lastCursor: Int): Observable<Topics> {
        return service.getTopics(lastCursor, AppConstants.PER_PAGE_SIZE)
    }

}
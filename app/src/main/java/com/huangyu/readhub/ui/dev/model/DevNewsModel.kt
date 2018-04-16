package com.huangyu.readhub.ui.dev.model

import com.huangyu.readhub.data.bean.TechNews
import com.huangyu.readhub.data.net.ApiService
import com.huangyu.readhub.mvp.BaseModel
import com.huangyu.readhub.util.AppConstants
import io.reactivex.Observable
import javax.inject.Inject

class DevNewsModel @Inject internal constructor(private val service: ApiService) : BaseModel(), IDevNewsModel {

    override fun queryDevNews(): Observable<TechNews> {
        return service.getDevNews(AppConstants.PER_PAGE_SIZE)
    }

    override fun queryDevNews(lastCursor: Long): Observable<TechNews> {
        return service.getDevNews(lastCursor, AppConstants.PER_PAGE_SIZE)
    }

}
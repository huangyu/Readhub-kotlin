package com.huangyu.readhub.ui.tech.model

import com.huangyu.readhub.data.bean.TechNews
import com.huangyu.readhub.data.net.ApiService
import com.huangyu.readhub.mvp.BaseModel
import com.huangyu.readhub.util.AppConstants
import io.reactivex.Observable
import javax.inject.Inject

class TechNewsModel @Inject internal constructor(private val service: ApiService) : BaseModel(), ITechNewsModel {

    override fun queryTechNews(): Observable<TechNews> {
        return service.getTechNews(AppConstants.PER_PAGE_SIZE)
    }

    override fun queryTechNews(lastCursor: Long): Observable<TechNews> {
        return service.getTechNews(lastCursor, AppConstants.PER_PAGE_SIZE)
    }

}
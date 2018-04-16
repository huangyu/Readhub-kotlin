package com.huangyu.readhub.ui.tech.model

import com.huangyu.readhub.data.bean.TechNews
import com.huangyu.readhub.mvp.IModel
import io.reactivex.Observable

interface ITechNewsModel : IModel {

    fun queryTechNews(): Observable<TechNews>

    fun queryTechNews(lastCursor: Long): Observable<TechNews>

}
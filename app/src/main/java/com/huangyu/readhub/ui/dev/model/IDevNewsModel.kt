package com.huangyu.readhub.ui.dev.model

import com.huangyu.readhub.data.bean.TechNews
import com.huangyu.readhub.mvp.IModel
import io.reactivex.Observable

interface IDevNewsModel : IModel {

    fun queryDevNews(): Observable<TechNews>

    fun queryDevNews(lastCursor: Long): Observable<TechNews>

}
package com.huangyu.readhub.ui.hot.model

import com.huangyu.readhub.data.bean.Topics
import com.huangyu.readhub.mvp.IModel
import io.reactivex.Observable

interface IHotTopicsModel : IModel {

    fun queryHotTopics(): Observable<Topics>

    fun queryHotTopics(lastCursor: Int): Observable<Topics>

}
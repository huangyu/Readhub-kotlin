package com.huangyu.readhub.ui.blockchain.model

import com.huangyu.readhub.data.bean.TechNews
import com.huangyu.readhub.mvp.IModel
import io.reactivex.Observable

interface IBlockChainModel : IModel {

    fun queryBlockChainNews(): Observable<TechNews>

    fun queryBlockChainNews(lastCursor: Long): Observable<TechNews>

}
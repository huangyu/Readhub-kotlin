package com.huangyu.readhub.ui.blockchain.model

import com.huangyu.readhub.data.bean.TechNews
import com.huangyu.readhub.data.net.ApiService
import com.huangyu.readhub.mvp.BaseModel
import com.huangyu.readhub.util.AppConstants
import io.reactivex.Observable
import javax.inject.Inject

class BlockChainModel @Inject internal constructor(private val service: ApiService) : BaseModel(), IBlockChainModel {

    override fun queryBlockChainNews(): Observable<TechNews> {
        return service.getBlockChainNews(AppConstants.PER_PAGE_SIZE)
    }

    override fun queryBlockChainNews(lastCursor: Long): Observable<TechNews> {
        return service.getBlockChainNews(lastCursor, AppConstants.PER_PAGE_SIZE)
    }

}
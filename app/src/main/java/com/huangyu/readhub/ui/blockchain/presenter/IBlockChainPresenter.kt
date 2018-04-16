package com.huangyu.readhub.ui.blockchain.presenter

import com.huangyu.readhub.mvp.IPresenter
import com.huangyu.readhub.ui.blockchain.model.IBlockChainModel
import com.huangyu.readhub.ui.blockchain.view.IBlockChainView

interface IBlockChainPresenter<V : IBlockChainView, M : IBlockChainModel> : IPresenter<V, M> {

    fun queryBlockChainNews(vararg lastCursor: Long)

}
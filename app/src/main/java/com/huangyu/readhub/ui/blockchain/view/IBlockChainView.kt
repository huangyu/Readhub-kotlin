package com.huangyu.readhub.ui.blockchain.view

import com.huangyu.readhub.data.bean.TechNew
import com.huangyu.readhub.mvp.IView

interface IBlockChainView : IView {

    fun initList(list: List<TechNew>)

    fun appendList(list: List<TechNew>)

    fun onRefreshError(msg: Throwable)

    fun onLoadError(msg: Throwable)

}
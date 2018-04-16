package com.huangyu.readhub.ui.blockchain.module

import com.huangyu.readhub.ui.blockchain.model.BlockChainModel
import com.huangyu.readhub.ui.blockchain.model.IBlockChainModel
import com.huangyu.readhub.ui.blockchain.presenter.BlockChainPresenter
import com.huangyu.readhub.ui.blockchain.presenter.IBlockChainPresenter
import com.huangyu.readhub.ui.blockchain.view.IBlockChainView
import dagger.Module
import dagger.Provides

/**
 * Created by huangyu on 2018/4/13.
 */
@Module
class BlockChainModule {

    @Provides
    internal fun provideBlockChainModel(model: BlockChainModel): IBlockChainModel = model

    @Provides
    internal fun provideBlockChainPresenter(presenter: BlockChainPresenter<IBlockChainView, IBlockChainModel>): IBlockChainPresenter<IBlockChainView, IBlockChainModel> = presenter

}
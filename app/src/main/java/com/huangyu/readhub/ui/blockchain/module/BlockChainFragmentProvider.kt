package com.huangyu.readhub.ui.blockchain.module

import com.huangyu.readhub.ui.blockchain.BlockChainNewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BlockChainFragmentProvider {

    @ContributesAndroidInjector(modules = [BlockChainModule::class])
    internal abstract fun provideBlockChainFragment(): BlockChainNewsFragment

}
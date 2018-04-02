package com.huangyu.readhub.ui.hot.module

import com.huangyu.readhub.ui.hot.fragment.HotTopicsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HotTopicsFragmentProvider {

    @ContributesAndroidInjector(modules = [HotTopicsModule::class])
    internal abstract fun provideHotTopicsFragment(): HotTopicsFragment

}
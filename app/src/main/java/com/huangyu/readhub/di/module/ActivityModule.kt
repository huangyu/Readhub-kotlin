package com.huangyu.readhub.di.module

import com.huangyu.readhub.ui.detail.DetailActivity
import com.huangyu.readhub.ui.detail.module.DetailModule
import com.huangyu.readhub.ui.hot.module.HotTopicsFragmentProvider
import com.huangyu.readhub.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by huangyu on 2018/3/28.
 */
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [(HotTopicsFragmentProvider::class)])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(DetailModule::class)])
    abstract fun bindDetailActivity(): DetailActivity

}
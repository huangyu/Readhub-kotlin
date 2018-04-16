package com.huangyu.readhub.ui.dev.module

import com.huangyu.readhub.ui.dev.DevNewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DevNewsFragmentProvider {

    @ContributesAndroidInjector(modules = [DevNewsModule::class])
    internal abstract fun provideDevNewsFragment(): DevNewsFragment

}
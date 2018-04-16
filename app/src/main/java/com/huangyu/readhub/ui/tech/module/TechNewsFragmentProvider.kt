package com.huangyu.readhub.ui.tech.module

import com.huangyu.readhub.ui.tech.TechNewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TechNewsFragmentProvider {

    @ContributesAndroidInjector(modules = [TechNewsModule::class])
    internal abstract fun provideTechNewsFragment(): TechNewsFragment

}
package com.huangyu.readhub.di.component

import android.app.Application
import com.huangyu.readhub.base.BaseApplication
import com.huangyu.readhub.di.module.ActivityModule
import com.huangyu.readhub.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by huangyu on 2018/3/28.
 */
@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (ActivityModule::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: BaseApplication)

}
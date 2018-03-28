package com.huangyu.readhub.di.module

import android.app.Application
import android.content.Context
import com.huangyu.readhub.data.net.ApiClient
import com.huangyu.readhub.data.net.ApiService
import com.huangyu.readhub.util.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by huangyu on 2018/3/28.
 */
@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideApiClient(apiClient: ApiClient): ApiService = apiClient.create()

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()

}
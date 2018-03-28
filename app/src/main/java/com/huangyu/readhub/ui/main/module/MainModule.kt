package com.huangyu.readhub.ui.main.module

import com.huangyu.readhub.ui.main.model.IMainModel
import com.huangyu.readhub.ui.main.model.MainModel
import com.huangyu.readhub.ui.main.presenter.IMainPresenter
import com.huangyu.readhub.ui.main.presenter.MainPresenter
import com.huangyu.readhub.ui.main.view.IMainView
import dagger.Module
import dagger.Provides

/**
 * Created by huangyu on 2018/3/28.
 */
@Module
class MainModule {

    @Provides
    internal fun provideMainModel(model: MainModel): IMainModel = model

    @Provides
    internal fun provideMainPresenter(presenter: MainPresenter<IMainView, IMainModel>): IMainPresenter<IMainView, IMainModel> = presenter

}
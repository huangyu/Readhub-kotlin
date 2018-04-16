package com.huangyu.readhub.ui.dev.module

import com.huangyu.readhub.ui.dev.model.DevNewsModel
import com.huangyu.readhub.ui.dev.model.IDevNewsModel
import com.huangyu.readhub.ui.dev.presenter.DevNewsPresenter
import com.huangyu.readhub.ui.dev.presenter.IDevNewsPresenter
import com.huangyu.readhub.ui.dev.view.IDevNewsView
import dagger.Module
import dagger.Provides

/**
 * Created by huangyu on 2018/4/13.
 */
@Module
class DevNewsModule {

    @Provides
    internal fun provideTechNewsModel(model: DevNewsModel): IDevNewsModel = model

    @Provides
    internal fun provideTechNewsPresenter(presenter: DevNewsPresenter<IDevNewsView, IDevNewsModel>): IDevNewsPresenter<IDevNewsView, IDevNewsModel> = presenter

}
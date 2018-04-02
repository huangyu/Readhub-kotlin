package com.huangyu.readhub.ui.hot.module

import com.huangyu.readhub.ui.hot.model.HotTopicsModel
import com.huangyu.readhub.ui.hot.model.IHotTopicsModel
import com.huangyu.readhub.ui.hot.presenter.HotTopicsPresenter
import com.huangyu.readhub.ui.hot.presenter.IHotTopicsPresenter
import com.huangyu.readhub.ui.hot.view.IHotTopicsView
import dagger.Module
import dagger.Provides

/**
 * Created by huangyu on 2018/3/28.
 */
@Module
class HotTopicsModule {

    @Provides
    internal fun provideHotTopicsModel(model: HotTopicsModel): IHotTopicsModel = model

    @Provides
    internal fun provideHotTopicsPresenter(presenter: HotTopicsPresenter<IHotTopicsView, IHotTopicsModel>): IHotTopicsPresenter<IHotTopicsView, IHotTopicsModel> = presenter

}
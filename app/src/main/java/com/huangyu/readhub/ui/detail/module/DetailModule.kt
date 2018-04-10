package com.huangyu.readhub.ui.detail.module

import com.huangyu.readhub.ui.detail.model.DetailModel
import com.huangyu.readhub.ui.detail.model.IDetailModel
import com.huangyu.readhub.ui.detail.presenter.DetailPresenter
import com.huangyu.readhub.ui.detail.presenter.IDetailPresenter
import com.huangyu.readhub.ui.detail.view.IDetailView
import dagger.Module
import dagger.Provides

/**
 * Created by huangyu on 2018/3/28.
 */
@Module
class DetailModule {

    @Provides
    internal fun provideDetailModel(model: DetailModel): IDetailModel = model

    @Provides
    internal fun provideDetailPresenter(presenter: DetailPresenter<IDetailView, IDetailModel>): IDetailPresenter<IDetailView, IDetailModel> = presenter

}
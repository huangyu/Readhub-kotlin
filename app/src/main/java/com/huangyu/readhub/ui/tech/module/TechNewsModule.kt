package com.huangyu.readhub.ui.tech.module

import com.huangyu.readhub.ui.tech.model.ITechNewsModel
import com.huangyu.readhub.ui.tech.model.TechNewsModel
import com.huangyu.readhub.ui.tech.presenter.ITechNewsPresenter
import com.huangyu.readhub.ui.tech.presenter.TechNewsPresenter
import com.huangyu.readhub.ui.tech.view.ITechNewsView
import dagger.Module
import dagger.Provides

/**
 * Created by huangyu on 2018/4/13.
 */
@Module
class TechNewsModule {

    @Provides
    internal fun provideTechNewsModel(model: TechNewsModel): ITechNewsModel = model

    @Provides
    internal fun provideTechNewsPresenter(presenter: TechNewsPresenter<ITechNewsView, ITechNewsModel>): ITechNewsPresenter<ITechNewsView, ITechNewsModel> = presenter

}
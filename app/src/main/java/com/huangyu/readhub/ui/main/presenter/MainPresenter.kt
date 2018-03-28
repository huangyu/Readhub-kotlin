package com.huangyu.readhub.ui.main.presenter

import com.huangyu.readhub.mvp.BasePresenter
import com.huangyu.readhub.ui.main.model.IMainModel
import com.huangyu.readhub.ui.main.view.IMainView
import com.huangyu.readhub.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by huangyu on 2018/3/28.
 */
class MainPresenter<V : IMainView, M : IMainModel> @Inject internal constructor(model: M, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) : BasePresenter<V, M>(model, schedulerProvider, disposable), IMainPresenter<V, M> {

}
package com.huangyu.readhub.ui.dev.presenter

import com.huangyu.readhub.mvp.BasePresenter
import com.huangyu.readhub.ui.dev.model.IDevNewsModel
import com.huangyu.readhub.ui.dev.view.IDevNewsView
import com.huangyu.readhub.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DevNewsPresenter<V : IDevNewsView, M : IDevNewsModel> @Inject internal constructor(model: M, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable)
    : BasePresenter<V, M>(model, schedulerProvider, compositeDisposable), IDevNewsPresenter<V, M> {

    override fun queryDevNews(vararg lastCursor: Long) {
        model?.let {
            if (lastCursor.isEmpty()) {
                compositeDisposable.add(
                        it.queryDevNews()
                                .compose(schedulerProvider.ioToMainObservableScheduler())
                                .subscribe(
                                        { devNews ->
                                            getView()?.initList(devNews.data)
                                        },
                                        { _error ->
                                            getView()?.onRefreshError(_error)
                                        }
                                )
                )
            } else {
                compositeDisposable.add(
                        it.queryDevNews(lastCursor[0])
                                .compose(schedulerProvider.ioToMainObservableScheduler())
                                .subscribe(
                                        { devNews ->
                                            getView()?.appendList(devNews.data)
                                        },
                                        { _error ->
                                            getView()?.onLoadError(_error)
                                        }
                                )
                )
            }
        }
    }

}
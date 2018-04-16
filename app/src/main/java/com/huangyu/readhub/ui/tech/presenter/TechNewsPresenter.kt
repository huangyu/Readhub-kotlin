package com.huangyu.readhub.ui.tech.presenter

import com.huangyu.readhub.mvp.BasePresenter
import com.huangyu.readhub.ui.tech.model.ITechNewsModel
import com.huangyu.readhub.ui.tech.view.ITechNewsView
import com.huangyu.readhub.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class TechNewsPresenter<V : ITechNewsView, M : ITechNewsModel> @Inject internal constructor(model: M, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable)
    : BasePresenter<V, M>(model, schedulerProvider, compositeDisposable), ITechNewsPresenter<V, M> {

    override fun queryTechNews(vararg lastCursor: Long) {
        model?.let {
            if (lastCursor.isEmpty()) {
                compositeDisposable.add(
                        it.queryTechNews()
                                .delay(250, TimeUnit.MILLISECONDS)
                                .compose(schedulerProvider.ioToMainObservableScheduler())
                                .subscribe(
                                        { techNews ->
                                            getView()?.initList(techNews.data)
                                        },
                                        { _error ->
                                            getView()?.onRefreshError(_error)
                                        }
                                )
                )
            } else {
                compositeDisposable.add(
                        it.queryTechNews(lastCursor[0])
                                .delay(250, TimeUnit.MILLISECONDS)
                                .compose(schedulerProvider.ioToMainObservableScheduler())
                                .subscribe(
                                        { techNews ->
                                            getView()?.appendList(techNews.data)
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
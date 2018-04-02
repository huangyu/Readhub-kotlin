package com.huangyu.readhub.ui.hot.presenter

import com.huangyu.readhub.mvp.BasePresenter
import com.huangyu.readhub.ui.hot.model.IHotTopicsModel
import com.huangyu.readhub.ui.hot.view.IHotTopicsView
import com.huangyu.readhub.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by huangyu on 2018/3/29.
 */
class HotTopicsPresenter<V : IHotTopicsView, M : IHotTopicsModel> @Inject internal constructor(model: M, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable)
    : BasePresenter<V, M>(model, schedulerProvider, compositeDisposable), IHotTopicsPresenter<V, M> {

    override fun queryHotTopics(vararg lastCursor: Int) {
        model?.let {
            if (lastCursor.isEmpty()) {
                compositeDisposable.add(
                        it.queryHotTopics()
                                .delay(500, TimeUnit.MILLISECONDS)
                                .compose(schedulerProvider.ioToMainObservableScheduler())
                                .subscribe(
                                        { _topics ->
                                            getView()?.initList(_topics.data)
                                        },
                                        { _error ->
                                            getView()?.onRefreshError(_error)
                                        }
                                )
                )
            } else {
                compositeDisposable.add(
                        it.queryHotTopics(lastCursor[0])
                                .delay(500, TimeUnit.MILLISECONDS)
                                .compose(schedulerProvider.ioToMainObservableScheduler())
                                .subscribe(
                                        { _topics ->
                                            getView()?.appendList(_topics.data)
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
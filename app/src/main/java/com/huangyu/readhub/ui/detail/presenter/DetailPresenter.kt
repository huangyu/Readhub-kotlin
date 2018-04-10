package com.huangyu.readhub.ui.detail.presenter

import com.huangyu.readhub.mvp.BasePresenter
import com.huangyu.readhub.ui.detail.model.IDetailModel
import com.huangyu.readhub.ui.detail.view.IDetailView
import com.huangyu.readhub.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by huangyu on 2018/4/10.
 */
class DetailPresenter<V : IDetailView, M : IDetailModel> @Inject internal constructor(model: M, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable)
    : BasePresenter<V, M>(model, schedulerProvider, compositeDisposable), IDetailPresenter<V, M> {

    override fun queryTopicDetail(topicId: String) {
        model?.let {
            compositeDisposable.add(
                    it.queryDetail(topicId)
                            .compose(schedulerProvider.ioToMainObservableScheduler())
                            .subscribe(
                                    { _topicDetail ->
                                        getView()?.onRefreshFinish(_topicDetail)
                                    },
                                    { _error ->
                                        getView()?.onRefreshError(_error)
                                    }
                            )
            )
        }
    }

}
package com.huangyu.readhub.ui.blockchain.presenter

import com.huangyu.readhub.mvp.BasePresenter
import com.huangyu.readhub.ui.blockchain.model.IBlockChainModel
import com.huangyu.readhub.ui.blockchain.view.IBlockChainView
import com.huangyu.readhub.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class BlockChainPresenter<V : IBlockChainView, M : IBlockChainModel> @Inject internal constructor(model: M, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable)
    : BasePresenter<V, M>(model, schedulerProvider, compositeDisposable), IBlockChainPresenter<V, M> {

    override fun queryBlockChainNews(vararg lastCursor: Long) {
        model?.let {
            if (lastCursor.isEmpty()) {
                compositeDisposable.add(
                        it.queryBlockChainNews()
                                .compose(schedulerProvider.ioToMainObservableScheduler())
                                .subscribe(
                                        { bcNews ->
                                            getView()?.initList(bcNews.data)
                                        },
                                        { _error ->
                                            getView()?.onRefreshError(_error)
                                        }
                                )
                )
            } else {
                compositeDisposable.add(
                        it.queryBlockChainNews(lastCursor[0])
                                .compose(schedulerProvider.ioToMainObservableScheduler())
                                .subscribe(
                                        { bcNews ->
                                            getView()?.appendList(bcNews.data)
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
package com.huangyu.readhub.mvp

import com.huangyu.readhub.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by huangyu on 2018/3/28.
 */
abstract class BasePresenter<V : IView, M : IModel> internal constructor(protected var model: M?, protected val schedulerProvider: SchedulerProvider, protected val compositeDisposable: CompositeDisposable) :IPresenter<V, M> {

    private var view: V? = null
    private val isViewAttached: Boolean get() = view != null

    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun getView(): V? = view

    override fun onDetach() {
        compositeDisposable.dispose()
        view = null
        model = null
    }

}
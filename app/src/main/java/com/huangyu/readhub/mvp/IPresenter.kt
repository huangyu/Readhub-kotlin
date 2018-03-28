package com.huangyu.readhub.mvp

/**
 * Created by huangyu on 2018/3/28.
 */
interface IPresenter<V : IView, M : IModel> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

}
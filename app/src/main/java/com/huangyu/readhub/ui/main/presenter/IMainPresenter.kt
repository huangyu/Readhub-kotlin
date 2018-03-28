package com.huangyu.readhub.ui.main.presenter

import com.huangyu.readhub.mvp.IPresenter
import com.huangyu.readhub.ui.main.model.IMainModel
import com.huangyu.readhub.ui.main.view.IMainView

/**
 * Created by huangyu on 2018/3/28.
 */
interface IMainPresenter<V : IMainView, M : IMainModel> : IPresenter<V, M> {

}
package com.huangyu.readhub.ui.main.view

import android.os.Bundle
import com.huangyu.readhub.R
import com.huangyu.readhub.base.BaseActivity
import com.huangyu.readhub.ui.main.model.IMainModel
import com.huangyu.readhub.ui.main.presenter.MainPresenter
import javax.inject.Inject

class MainActivity : BaseActivity(), IMainView {

    @Inject
    lateinit var presenter: MainPresenter<IMainView, IMainModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

}

package com.huangyu.readhub.ui.dev.fragment

import com.huangyu.readhub.R
import com.huangyu.readhub.base.LazyLoadFragment

/**
 * Created by huangyu on 2018/3/28.
 */
class DevNewsFragment : LazyLoadFragment() {

    companion object {
        fun newInstance(): DevNewsFragment {
            return DevNewsFragment()
        }
    }

    override fun setContentView(): Int = R.layout.fragment_dev

    override fun lazyLoad() {
    }

    override fun stopLoad() {
    }

}
package com.huangyu.readhub.ui.blockchain.fragment

import com.huangyu.readhub.R
import com.huangyu.readhub.base.LazyLoadFragment

/**
 * Created by huangyu on 2018/3/28.
 */
class BlackChainNewsFragment : LazyLoadFragment() {

    companion object {
        fun newInstance(): BlackChainNewsFragment {
            return BlackChainNewsFragment()
        }
    }

    override fun setContentView(): Int = R.layout.fragment_bc

    override fun lazyLoad() {
    }

    override fun stopLoad() {
    }

}
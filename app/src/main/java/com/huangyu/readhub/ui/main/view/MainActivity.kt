package com.huangyu.readhub.ui.main.view

import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.huangyu.readhub.R
import com.huangyu.readhub.base.BaseActivity
import com.huangyu.readhub.ui.bc.fragment.BlackChainNewsFragment
import com.huangyu.readhub.ui.dev.fragment.DevNewsFragment
import com.huangyu.readhub.ui.hot.fragment.HotTopicsFragment
import com.huangyu.readhub.ui.main.adapter.ViewPagerAdapter
import com.huangyu.readhub.ui.tech.fragment.TechNewsFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = injector

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {
        view_pager.adapter = ViewPagerAdapter(listOf(
                HotTopicsFragment.newInstance(),
                TechNewsFragment.newInstance(),
                DevNewsFragment.newInstance(),
                BlackChainNewsFragment.newInstance()),
                supportFragmentManager)

        bottom_navigation_bar.activeColor = R.color.colorPrimary
        bottom_navigation_bar
                .addItem(BottomNavigationItem(R.mipmap.ic_hot, getString(R.string.hot_topics)))
                .addItem(BottomNavigationItem(R.mipmap.ic_tech, getString(R.string.tech_news)))
                .addItem(BottomNavigationItem(R.mipmap.ic_dev, getString(R.string.dev_news)))
                .addItem(BottomNavigationItem(R.mipmap.ic_bc, getString(R.string.block_chain_news)))
                .initialise()
    }

    override fun initListeners() {
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                bottom_navigation_bar.selectTab(position)
            }

        })

        bottom_navigation_bar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
            override fun onTabSelected(position: Int) {
                view_pager.currentItem = position
            }

            override fun onTabReselected(position: Int) {

            }

            override fun onTabUnselected(position: Int) {

            }
        })
    }

}

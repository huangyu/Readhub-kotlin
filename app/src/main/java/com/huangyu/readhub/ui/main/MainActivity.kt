package com.huangyu.readhub.ui.main

import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.KeyEvent
import android.widget.Toast
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.huangyu.readhub.R
import com.huangyu.readhub.base.BaseInjectActivity
import com.huangyu.readhub.ui.blockchain.fragment.BlackChainNewsFragment
import com.huangyu.readhub.ui.dev.fragment.DevNewsFragment
import com.huangyu.readhub.ui.hot.fragment.HotTopicsFragment
import com.huangyu.readhub.ui.main.adapter.ViewPagerAdapter
import com.huangyu.readhub.ui.tech.TechNewsFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseInjectActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Fragment>

    private var firstTime: Long = 0L

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

    override fun initData() {
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

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event!!.action == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > 2000) {
                Toast.makeText(this, getString(R.string.tips_exit), Toast.LENGTH_SHORT).show()
                firstTime = System.currentTimeMillis()
            } else {
                finish()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}

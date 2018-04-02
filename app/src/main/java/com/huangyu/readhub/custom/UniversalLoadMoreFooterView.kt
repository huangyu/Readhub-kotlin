package com.huangyu.readhub.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.huangyu.readhub.R

class UniversalLoadMoreFooterView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    public var status: Status? = null
        set(status) {
            field = status
            change()
        }

    private val mLoadingView: View

    private val mErrorView: View

    private val mTheEndView: View

    private var mOnRetryListener: OnRetryListener? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.load_more_footer_view, this, true)

        mLoadingView = findViewById(R.id.loadingView)
        mErrorView = findViewById(R.id.errorView)
        mTheEndView = findViewById(R.id.theEndView)

        mErrorView.setOnClickListener {
            if (mOnRetryListener != null) {
                mOnRetryListener!!.onRetry(this@UniversalLoadMoreFooterView)
            }
        }
    }

    fun setOnRetryListener(listener: OnRetryListener) {
        this.mOnRetryListener = listener
    }

    fun canLoadMore(): Boolean {
        return status == Status.GONE || status == Status.ERROR
    }

    private fun change() {
        when (status) {
            UniversalLoadMoreFooterView.Status.GONE -> {
                mLoadingView.visibility = View.GONE
                mErrorView.visibility = View.GONE
                mTheEndView.visibility = View.GONE
            }

            UniversalLoadMoreFooterView.Status.LOADING -> {
                mLoadingView.visibility = View.VISIBLE
                mErrorView.visibility = View.GONE
                mTheEndView.visibility = View.GONE
            }

            UniversalLoadMoreFooterView.Status.ERROR -> {
                mLoadingView.visibility = View.GONE
                mErrorView.visibility = View.VISIBLE
                mTheEndView.visibility = View.GONE
            }

            UniversalLoadMoreFooterView.Status.THE_END -> {
                mLoadingView.visibility = View.GONE
                mErrorView.visibility = View.GONE
                mTheEndView.visibility = View.VISIBLE
            }
        }
    }

    enum class Status {
        GONE, LOADING, ERROR, THE_END
    }

    interface OnRetryListener {
        fun onRetry(view: UniversalLoadMoreFooterView)
    }

}
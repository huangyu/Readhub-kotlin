package com.huangyu.readhub.base

import android.support.annotation.IdRes
import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun onBind(t: T, position: Int)

    @Suppress("UNCHECKED_CAST")
    fun <T : View> findViewById(@IdRes id: Int): T {
        return itemView.findViewById<View>(id) as T
    }

}
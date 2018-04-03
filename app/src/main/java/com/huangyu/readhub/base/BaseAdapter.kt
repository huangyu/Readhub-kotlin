package com.huangyu.readhub.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class BaseAdapter<T>(val context: Context?, var list: MutableList<T>? = ArrayList()) : RecyclerView.Adapter<BaseViewHolder<T?>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T?> {
        val layoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return onCreateViewHolder(layoutInflater, parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T?>, position: Int) {
        holder.onBind(getItem(position), position)
    }

    override fun getItemCount(): Int {
        return if (list == null) 0 else list!!.size
    }

    abstract fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): BaseViewHolder<T?>

    fun getItem(position: Int): T? {
        return if (list == null) null else list!![position]
    }

    fun clearList() {
        this.list!!.clear()
        this.notifyDataSetChanged()
    }

    fun initList(l: List<T>) {
        this.list!!.clear()
        this.list!!.addAll(l)
        this.notifyDataSetChanged()
    }

    fun appendList(l: List<T>) {
        this.list!!.addAll(l)
        this.notifyItemRangeInserted(itemCount, itemCount + l.size)
    }

}
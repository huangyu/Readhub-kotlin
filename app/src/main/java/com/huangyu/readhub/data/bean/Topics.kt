package com.huangyu.readhub.data.bean

/**
 * Created by huangyu on 2018/3/28.
 */
data class Topics(
        var data: List<Topic>,
        var pageSize: Int,
        var totalItems: Int,
        var totalPages: Int
)
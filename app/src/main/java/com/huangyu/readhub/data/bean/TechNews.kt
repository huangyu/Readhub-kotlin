package com.huangyu.readhub.data.bean

data class TechNews(
        var data: List<TechNew>,
        var pageSize: Int,
        var totalItems: Int,
        var totalPages: Int
)
package com.huangyu.readhub.data.bean

/**
 * Created by huangyu on 2018/3/28.
 */
data class News(
        var id: Int,
        var url: String,
        var title: String,
        var groupId: String,
        var stateName: String,
        var siteSlug: String,
        var mobileUrl: String,
        var authorName: String,
        var duplicateId: Int,
        var publishDate: String
)
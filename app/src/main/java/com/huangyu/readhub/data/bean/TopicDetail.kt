package com.huangyu.readhub.data.bean

/**
 * Created by huangyu on 2018/3/28.
 */
data class TopicDetail(
        var id: String,
        var createdAt: String,
        var order: Int,
        var publishDate: String,
        var summary: String,
        var title: String,
        var updatedAt: String,
        var newsArray: List<News>,
        var timeline: TimeLine,
        var hasInstantView: Boolean = false
)
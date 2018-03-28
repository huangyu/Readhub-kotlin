package com.huangyu.readhub.data.bean

/**
 * Created by huangyu on 2018/3/28.
 */
data class TimeLine(
        var topics: List<TopicSummary>,
        var message: String,
        var errorCode: Int
)
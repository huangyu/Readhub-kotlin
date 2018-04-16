package com.huangyu.readhub.data.net

import com.huangyu.readhub.data.bean.TechNews
import com.huangyu.readhub.data.bean.TopicDetail
import com.huangyu.readhub.data.bean.Topics
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by huangyu on 2018/3/28.
 */
interface ApiService {

    @GET("topic")
    fun getTopics(@Query("lastCursor") lastCursor: Int, @Query("pageSize") pageSize: Int): Observable<Topics>

    @GET("topic")
    fun getTopics(@Query("pageSize") pageSize: Int): Observable<Topics>

    @GET("topic/{topicId}")
    fun getTopicDetail(@Path("topicId") topicId: String): Observable<TopicDetail>

    @GET("news")
    fun getTechNews(@Query("lastCursor") lastCursor: Long, @Query("pageSize") pageSize: Int): Observable<TechNews>

    @GET("news")
    fun getTechNews(@Query("pageSize") pageSize: Int): Observable<TechNews>

}
package com.flywith24.demo_paging

import androidx.room.Entity
import androidx.room.PrimaryKey
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author yyz (杨云召)
 * @date   2020/5/8
 * time   14:56
 * description
 */
interface Api {
    /*首页文章列表*/
    @GET("article/list/{pageSize}/json")
    suspend fun getHomeArticleList(@Path("pageSize") pageSize: Int): Response<Article>

    data class Response<T>(
        var data: T?,
        var errorCode: String,
        var errorMsg: String
    )

    data class Article(
        val curPage: Int,
        val datas: List<Data>,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val size: Int,
        val total: Int
    )

    @Entity
    data class Data(
        val desc: String,
        val id: Int,
        val title: String
    ) {
        @PrimaryKey(autoGenerate = true)
        var indexInResponse: Int = -1
    }

    data class Tag(
        val name: String,
        val url: String
    )

    companion object {
        fun create() = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<Api>()
    }
}
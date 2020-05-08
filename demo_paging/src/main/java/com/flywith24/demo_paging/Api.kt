package com.flywith24.demo_paging

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

    data class Data(
        val apkLink: String,
        val audit: Int,
        val author: String,
        val canEdit: Boolean,
        val chapterId: Int,
        val chapterName: String,
        val collect: Boolean,
        val courseId: Int,
        val desc: String,
        val descMd: String,
        val envelopePic: String,
        val fresh: Boolean,
        val id: Int,
        val link: String,
        val niceDate: String,
        val niceShareDate: String,
        val origin: String,
        val prefix: String,
        val projectLink: String,
        val publishTime: Long,
        val selfVisible: Int,
        val shareDate: Long,
        val shareUser: String,
        val superChapterId: Int,
        val superChapterName: String,
        val tags: List<Tag>,
        val title: String,
        val type: Int,
        val userId: Int,
        val visible: Int,
        val zan: Int
    )

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
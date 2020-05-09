package com.flywith24.demo_paging.network

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.paging.PageKeyedDataSource
import com.flywith24.demo_paging.Api
import kotlinx.coroutines.launch

/**
 * @author yyz (杨云召)
 * @date   2020/5/8
 * time   15:02
 * description
 */
class MyDataSource(private val lifecycleScope: LifecycleCoroutineScope) :
    PageKeyedDataSource<Int, Api.Data>() {
    private val api = Api.create()
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Api.Data>
    ) {
        lifecycleScope.launch {
            val result = api.getHomeArticleList(0).data!!
            callback.onResult(result.datas, null, result.curPage)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Api.Data>) {
        lifecycleScope.launch {
            val result = api.getHomeArticleList(pageSize = params.key).data!!
            //pass the key for the next page, or null if the loaded page is the last.
            val adjacentPageKey = if (result.over) null else result.curPage
            callback.onResult(result.datas, adjacentPageKey)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Api.Data>) {
        lifecycleScope.launch {
            val result = api.getHomeArticleList(pageSize = params.key).data!!
            //pass the key for the previous page, or {@code null} if the loaded page is the first.
            val adjacentPageKey = if (params.key > 1) params.key - 1 else null
            callback.onResult(result.datas, adjacentPageKey)
        }
    }
}
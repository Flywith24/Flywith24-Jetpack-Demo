package com.flywith24.demo_paging.db

import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.paging.PagedList
import com.flywith24.demo_paging.Api

import com.flywith24.demo_paging.Api.Data
import com.flywith24.demo_paging.PAGE_SIZE
import kotlinx.coroutines.launch

/**
 * @author yyz (杨云召)
 * @date   2020/4/8
 * time   9:45
 * description
 */
class DataBoundaryCallback(
    private val lifecycleScope: LifecycleCoroutineScope,
    private val dao: DataDao
) :
    PagedList.BoundaryCallback<Data>() {
    override fun onZeroItemsLoaded() {
        lifecycleScope.launch {
            val result = Api.create().getHomeArticleList(0).data?.datas
            if (result != null) {
                dao.insertData(data = result)
            }
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: Data) {

        lifecycleScope.launch {
            Log.i(TAG, "onItemAtEndLoaded: itemAtEnd ${itemAtEnd.indexInResponse} ")
            val pageSize = (itemAtEnd.indexInResponse) / PAGE_SIZE
            Log.i(TAG, "onItemAtEndLoaded: $pageSize")
            val result =
                Api.create().getHomeArticleList(pageSize).data?.datas
            if (result != null) {
                dao.insertData(data = result)
            }
        }
    }

    override fun onItemAtFrontLoaded(itemAtFront: Data) {

        // ignore
    }

    companion object {
        private const val TAG = "DataBoundaryCallback"
    }
}
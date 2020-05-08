package com.flywith24.demo_paging

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.paging.DataSource

/**
 * @author yyz (杨云召)
 * @date   2020/5/8
 * time   15:03
 * description
 */
class MyDataFactory(private val scope: LifecycleCoroutineScope) :
    DataSource.Factory<Int, Api.Data>() {
    override fun create(): DataSource<Int, Api.Data> {
        return MyDataSource(scope)
    }
}
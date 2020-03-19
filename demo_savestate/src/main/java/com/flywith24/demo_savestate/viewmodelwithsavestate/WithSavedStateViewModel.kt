package com.flywith24.demo_savestate.viewmodelwithsavestate

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/**
 * @author yyz (杨云召)
 * @date   2020/3/19
 * time   21:43
 * description
 * 带 SavedState 的 ViewModel 进程杀死后数据不丢失
 */
class WithSavedStateViewModel(private val state: SavedStateHandle) : ViewModel() {
    private val key = "key"
    fun setValue(value: String) = state.set(key, value)
    fun getValue(): LiveData<String> = state.getLiveData(key)
}
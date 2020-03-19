package com.flywith24.demo_savestate.viewmodelwithoutsavedstate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author yyz (杨云召)
 * @date   2020/3/19
 * time   22:22
 * description
 * 不带 SavedState 的 ViewModel，数据保存在内存中，进程杀死后数据丢失
 */
class WithoutSavedStateViewModel : ViewModel() {
    private var _value = MutableLiveData<String>()
    fun setValue(value: String) {
        _value.value = value
    }

    fun getValue(): LiveData<String> = _value
}
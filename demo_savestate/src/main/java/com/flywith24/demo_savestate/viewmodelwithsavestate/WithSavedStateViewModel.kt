package com.flywith24.demo_savestate.viewmodelwithsavestate

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/**
 * @author yyz (杨云召)
 * @date   2020/3/19
 * time   21:43
 * description
 */
class WithSavedStateViewModel(private val state: SavedStateHandle) : ViewModel() {
    private val key = "key"

    var value: String?
        get() = state.get<String>(key)
        set(value) = state.set(key, value)
}
package com.flywith24.demo_lifecycle

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * @author yyz (杨云召)
 * @date   2020/3/30
 * time   10:07
 * description
 */
class PlayManager(
    lifecycleOwner: LifecycleOwner,
    private val listener: MainActivity.PlayStateListener
) :
    DefaultLifecycleObserver {

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    override fun onResume(owner: LifecycleOwner) {
        listener.onPlayStateChanged(true)
    }

    override fun onPause(owner: LifecycleOwner) {
        listener.onPlayStateChanged(false)
    }
}
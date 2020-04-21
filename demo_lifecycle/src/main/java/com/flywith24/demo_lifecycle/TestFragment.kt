package com.flywith24.demo_lifecycle

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * @author yyz (杨云召)
 * @date   2020/4/21
 * time   9:35
 * description
 */
class TestFragment : Fragment() {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                // 想做啥做点啥
                owner.lifecycle.removeObserver(this)
            }
        })
    }

}
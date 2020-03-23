package com.flywith24.demo_viewmodel

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels

/**
 * @author yyz (杨云召)
 * @date   2020/3/23
 * time   16:52
 * description
 */
class TestFragment : Fragment(R.layout.fragment_test) {
    //fragment 内部 ViewModel
    private val mViewModel by viewModels<MyViewModel>()

    //activity 范围内共享的 ViewModel
    private val mActivityViewModel by activityViewModels<MyViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //与 MainActivity 中的相同
        Log.i(TAG, "onViewCreated: activity 范围内共享的 ViewModel $mActivityViewModel")
        Log.i(TAG, "onViewCreated: fragment 内部 ViewModel $mViewModel")
    }

    companion object {
        private const val TAG = "TestFragment"
    }
}
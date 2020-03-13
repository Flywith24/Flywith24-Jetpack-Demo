package com.flywith24.library.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * @author yyz (杨云召)
 * @date   2020/3/4
 * time   9:58
 * description
 */
abstract class BaseFragment<T : ViewBinding>(@LayoutRes layoutId: Int) : Fragment(layoutId) {
    private var _binding: T? = null

    val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = initBinding(view)
        init(savedInstanceState)
    }

    /**
     * 初始化 [_binding]
     */
    abstract fun initBinding(view: View): T

    abstract fun init(savedInstanceState: Bundle?)


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

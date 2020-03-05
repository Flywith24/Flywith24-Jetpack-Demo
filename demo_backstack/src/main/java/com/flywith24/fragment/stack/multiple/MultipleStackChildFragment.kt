package com.flywith24.fragment.stack.multiple

import android.os.Bundle
import android.view.View
import com.flywith24.fragment.R
import com.flywith24.fragment.databinding.FragmentMultipleChildBinding
import com.flywith24.library.base.BaseFragment

/**
 * @author yyz (杨云召)
 * @date   2020/3/5
 * time   22:36
 * description 多返回栈 子fragment
 */
class MultipleStackChildFragment :
    BaseFragment<FragmentMultipleChildBinding>(R.layout.fragment_multiple_child) {
    override fun initBinding(view: View): FragmentMultipleChildBinding =
        FragmentMultipleChildBinding.bind(view)

    override fun init(savedInstanceState: Bundle?) {

    }
}
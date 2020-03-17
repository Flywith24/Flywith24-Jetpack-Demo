package com.flywith24.demo_savestate

import android.os.Bundle
import android.view.View
import com.flywith24.demo_savestate.databinding.FragentChildBinding

import com.flywith24.library.base.BaseFragment

class ChildFragment : BaseFragment<FragentChildBinding>(R.layout.fragent_child) {
    override fun initBinding(view: View): FragentChildBinding = FragentChildBinding.bind(view)

    override fun init(savedInstanceState: Bundle?) {

    }

}

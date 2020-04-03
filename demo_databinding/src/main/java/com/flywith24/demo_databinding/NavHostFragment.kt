package com.flywith24.demo_databinding

import android.os.Bundle
import android.view.View
import com.flywith24.demo_databinding.binding.DataBindingFragment
import com.flywith24.demo_databinding.databinding.FragmentNavHostBinding
import com.flywith24.demo_databinding.normal.NormalFragment
import com.flywith24.library.base.BaseFragment
import com.flywith24.library.base.ext.jumpToFragment

/**
 * @author yyz (杨云召)
 * @date   2020/4/3
 * time   13:53
 * description
 */
class NavHostFragment : BaseFragment<FragmentNavHostBinding>(R.layout.fragment_nav_host) {
    override fun initBinding(view: View): FragmentNavHostBinding = FragmentNavHostBinding.bind(view)

    override fun init(savedInstanceState: Bundle?) {
        binding.dataBinding.setOnClickListener {
            jumpToFragment<DataBindingFragment>(R.id.container)
        }
        binding.normal.setOnClickListener {
            jumpToFragment<NormalFragment>(R.id.container)
        }
    }
}

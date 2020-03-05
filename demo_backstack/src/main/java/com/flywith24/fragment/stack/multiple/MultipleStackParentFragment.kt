package com.flywith24.fragment.stack.multiple

import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import com.flywith24.fragment.R
import com.flywith24.fragment.databinding.FragmentMultipleParentBinding
import com.flywith24.library.base.BaseFragment

/**
 * @author yyz (杨云召)
 * @date   2020/3/5
 * time   22:36
 * description 多返回栈 父fragment
 */
class MultipleStackParentFragment :
    BaseFragment<FragmentMultipleParentBinding>(R.layout.fragment_multiple_parent) {
    override fun initBinding(view: View): FragmentMultipleParentBinding =
        FragmentMultipleParentBinding.bind(view)

    override fun init(savedInstanceState: Bundle?) {
        for (id in DESTINATIONS) {
            childFragmentManager.commitNow {
                val fragment =
                    MultipleStackChildFragment.newInstance(name(R.id.inner_container), 1)
                add(R.id.inner_container, fragment, fragment.stableTag)
            }
        }
        binding.tabs.addOnButtonCheckedListener { _, checkId, isChecked ->
            if (isChecked) {
                childFragmentManager.commit {

                }
                when (checkId) {
                    R.id.first -> {
                    }
                    R.id.second -> {
                    }
                    R.id.third -> {
                    }
                }
            }
        }
    }

    companion object {
        private val DESTINATIONS = intArrayOf(R.id.first, R.id.second, R.id.third)
    }
}
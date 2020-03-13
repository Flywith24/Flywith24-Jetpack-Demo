package com.flywith24.fragment.stack.single

import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import com.flywith24.fragment.R
import com.flywith24.fragment.databinding.FragmentSingleParentBinding

import com.flywith24.fragment.stack.common.BaseStackFragment
import com.flywith24.library.base.ext.addOnBackPressedCallback

/**
 * @author yyz (杨云召)
 * @date   2020/3/4
 * time   10:02
 * description  独立返回栈父fragment
 * parentFragment 包含四个返回栈，各个返回栈中显示若干[SingleStackChildFragment]
 */
class SingleStackParentFragment :
    BaseStackFragment<FragmentSingleParentBinding>(R.layout.fragment_single_parent) {
    override fun initBinding(view: View): FragmentSingleParentBinding =
        FragmentSingleParentBinding.bind(view)

    override fun init(savedInstanceState: Bundle?) {
        //返回键拦截
        addOnBackPressedCallback {
            isEnabled = childFragmentManager.backStackEntryCount != 0
//            if (isEnabled) childFragmentManager.popBackStack("♥", 1)
//            if (isEnabled) childFragmentManager.popBackStack("♥", 0)
            if (isEnabled) childFragmentManager.popBackStack()
            else requireActivity().onBackPressed()
        }

        if (savedInstanceState == null) {
            for (containerId in mStackIds) {
                childFragmentManager.commit {
                    val fragment =
                        SingleStackChildFragment.newInstance(
                            name(containerId),
                            1
                        )
                    replace(containerId, fragment, fragment.stableTag)
                }
            }
        }
    }
}
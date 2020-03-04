package com.flywith24.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.commit
import com.flywith24.fragment.databinding.FragmentParentBinding
import com.flywith24.library.base.BaseFragment
import com.flywith24.library.base.ext.addOnBackPressedCallback

/**
 * @author yyz (杨云召)
 * @date   2020/3/4
 * time   10:02
 * description
 * parentFragment 包含四个返回栈，各个返回栈中显示若干[ChildFragment]
 */
class ParentFragment : BaseFragment<FragmentParentBinding>(R.layout.fragment_parent) {
    override fun initBinding(view: View): FragmentParentBinding = FragmentParentBinding.bind(view)

    override fun init(savedInstanceState: Bundle?) {
        //返回键拦截
        addOnBackPressedCallback {
            isEnabled = childFragmentManager.backStackEntryCount != 0
            if (isEnabled) childFragmentManager.popBackStack()
            else requireActivity().onBackPressed()
        }

        if (savedInstanceState == null) {
            for (containerId in containerIds) {
                childFragmentManager.commit {
                    val fragment = ChildFragment.newInstance(name(containerId), 1)
                    replace(containerId, fragment, fragment.stableTag)
                }
            }
        }
    }

    private val containerIds = intArrayOf(R.id.stack_1, R.id.stack_2, R.id.stack_3, R.id.stack_4)
    private fun name(@IdRes containerId: Int) =
        resources.getResourceEntryName(containerId).replace("_", " ")

}
package com.flywith24.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.commit
import com.flywith24.fragment.databinding.FragmentParentBinding
import com.flywith24.library.base.BaseFragment

/**
 * @author yyz (杨云召)
 * @date   2020/3/4
 * time   10:02
 * description
 */
class ParentFragment : BaseFragment<FragmentParentBinding>(R.layout.fragment_parent) {
    override fun initBinding(view: View): FragmentParentBinding = FragmentParentBinding.bind(view)


    override fun init(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            for (containerId in CONTAINER_IDS) {
                childFragmentManager.commit {
                    val fragment = ChildFragment.newInstance(name(containerId), 1, containerId)
                    replace(containerId, fragment, fragment.stableTag)
                }
            }
        }
    }

    private val CONTAINER_IDS = intArrayOf(R.id.stack_1, R.id.stack_2, R.id.stack_3, R.id.stack_4)
    private fun name(@IdRes containerId: Int) =
        resources.getResourceEntryName(containerId).replace("_", " ")

}
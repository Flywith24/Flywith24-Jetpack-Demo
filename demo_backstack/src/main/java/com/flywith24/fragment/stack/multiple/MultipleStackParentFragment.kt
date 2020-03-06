package com.flywith24.fragment.stack.multiple

import android.os.Bundle
import android.util.Log
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

    lateinit var currentFragment: String

    private val stackList = ArrayList<StackFragment>()
    override fun init(savedInstanceState: Bundle?) {
        childFragmentManager.registerFragmentLifecycleCallbacks(
            StackFragment.StackLifecycleCallback(),
            false
        )
        currentFragment = name(DESTINATIONS[0])
        for (index in DESTINATIONS.indices) {
            childFragmentManager.commitNow {
                val fragment =
                    StackFragment.newInstance(index, name(DESTINATIONS[index]))
                stackList.add(fragment)
                add(R.id.inner_container, fragment, fragment.stableTag)
            }
        }
        /*    for (id in DESTINATIONS) {
                childFragmentManager.commitNow {
                    val fragment =
                        MultipleStackChildFragment.newInstance(name(id), 1)
                    add(R.id.inner_container, fragment, fragment.stableTag)
                }
            }*/
        binding.tabs.addOnButtonCheckedListener { _, checkId, isChecked ->
            if (isChecked) {
                currentFragment = name(checkId)
                childFragmentManager.commit {
                    transactions@ for (fragment in stackList)
                        when {
                            fragment.name == currentFragment && !fragment.isDetached -> continue@transactions
                            fragment.name == currentFragment && fragment.isDetached -> attach(
                                fragment
                            )
                            else -> if (!fragment.isDetached) detach(fragment)
                        }
                }
            }
        }
    }

    companion object {
        private val DESTINATIONS = intArrayOf(R.id.first, R.id.second, R.id.third)
    }
}
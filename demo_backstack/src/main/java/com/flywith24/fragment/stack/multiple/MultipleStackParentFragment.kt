package com.flywith24.fragment.stack.multiple

import android.os.Bundle
import android.view.View
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

    private val stackList = ArrayList<NavHostFragment>()
    override fun init(savedInstanceState: Bundle?) {

        currentFragment = name(DESTINATIONS[0])
        DESTINATIONS.forEachIndexed { index, id ->
            childFragmentManager.commitNow {
                val fragment = NavHostFragment.newInstance(index, name(id))
                stackList.add(fragment)
                add(R.id.inner_container, fragment, fragment.stableTag)
            }
        }
        binding.tabs.addOnButtonCheckedListener { _, checkId, isChecked ->
            if (isChecked) {
                childFragmentManager.commitNow {
                    stackList.forEach { item ->
                        if (name(checkId) == item.name) {
                            attach(item)
                            setPrimaryNavigationFragment(item)
                        } else {
                            detach(item)
                        }
                    }
                }
            }
        }
        binding.tabs.check(DESTINATIONS[0])
    }

    companion object {
        private val DESTINATIONS = intArrayOf(R.id.first, R.id.second, R.id.third)
        const val TAG = "yyz11"
    }
}
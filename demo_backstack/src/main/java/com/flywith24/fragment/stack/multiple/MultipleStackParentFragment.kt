package com.flywith24.fragment.stack.multiple

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.commitNow
import com.flywith24.fragment.R
import com.flywith24.fragment.databinding.FragmentMultipleParentBinding
import com.flywith24.library.base.BaseFragment
import java.util.*
import kotlin.collections.ArrayList

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

    /**
     * 返回栈顺序,存储返回栈id
     */
    private val orderStack = ArrayDeque<Int>()

    private val stackList = ArrayList<NavHostFragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //拦截返回键
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (!orderStack.isNullOrEmpty()) {
                        //移除栈顶 stackId
                        orderStack.removeFirst()
                        if (orderStack.isNotEmpty()) {
                            //将新的栈顶 显示
                            binding.tabs.check(orderStack.first)
                            return
                        }
                    }
                    requireActivity().supportFragmentManager.popBackStack()
                }
            })
    }

    override fun init(savedInstanceState: Bundle?) {

        //add NavHostFragment
        DESTINATIONS.forEachIndexed { index, id ->
            childFragmentManager.commitNow {
                val fragment = NavHostFragment.newInstance(index, name(id))
                stackList.add(fragment)
                add(R.id.inner_container, fragment, fragment.stableTag)
            }
        }
        binding.tabs.addOnButtonCheckedListener { _, checkId, isChecked ->
            if (isChecked) {
                //被选中的 先出栈，再入栈顶
                orderStack.remove(checkId)
                orderStack.push(checkId)
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
        //默认选中 first
        binding.tabs.check(DESTINATIONS[0])
    }

    companion object {
        private val DESTINATIONS = intArrayOf(R.id.first, R.id.second, R.id.third)
    }
}
package com.flywith24.fragment.stack.multiple

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import com.flywith24.fragment.R
import com.flywith24.fragment.databinding.FragmentStackBinding
import com.flywith24.library.base.BaseFragment
import com.flywith24.library.base.ext.addOnBackPressedCallback
import com.flywith24.library.base.ext.args

/**
 * @author yyz (杨云召)
 * @date   2020/3/6
 * time   13:38
 * description
 */
class StackFragment : BaseFragment<FragmentStackBinding>(R.layout.fragment_stack) {
    override fun initBinding(view: View): FragmentStackBinding = FragmentStackBinding.bind(view)

    val stableTag: String
        get() = "${javaClass.simpleName}-$index"

    internal var index: Int by args()
    var name: String by args()

    override fun init(savedInstanceState: Bundle?) {
        //返回键拦截
        addOnBackPressedCallback {
            isEnabled = childFragmentManager.backStackEntryCount != 0
            if (isEnabled) childFragmentManager.popBackStack()
            else requireActivity().onBackPressed()
        }

        childFragmentManager.commitNow {
            add(R.id.content, MultipleStackChildFragment.newInstance(name, 1))
        }
    }

    companion object {
        internal fun newInstance(index: Int, name: String) =
            StackFragment().apply {
                this.index = index
                this.name = name
            }
    }

    class StackLifecycleCallback : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentCreated(
            fm: FragmentManager,
            f: Fragment,
            savedInstanceState: Bundle?
        ) {
            if (f is StackFragment) {
                (f.parentFragment as? MultipleStackParentFragment)?.let {
                    if (it.currentFragment != f.name && !f.isDetached) {
                        fm.commit { detach(f) }
                    }
                }
            }
        }
    }
}
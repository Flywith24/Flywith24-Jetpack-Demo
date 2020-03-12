package com.flywith24.fragment.stack.multiple

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
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
 * 多返回栈意味着多 fragmentManager，因此使用多个无ui fragment 作为多返回栈载体
 */
class NavHostFragment : BaseFragment<FragmentStackBinding>(R.layout.fragment_stack) {
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
        if (childFragmentManager.findFragmentByTag("${name}1") == null) {
            Log.i(TAG, "commitNow: $name")
            childFragmentManager.commitNow {
                val fragment = MultipleStackChildFragment.newInstance(name, 1)
                setPrimaryNavigationFragment(fragment)
                add(R.id.content, fragment, "${name}1")
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i(TAG, "onAttach: $name")

    }

    override fun onDetach() {
        super.onDetach()
        Log.i(TAG, "onDetach: $name")
    }

    companion object {
        internal fun newInstance(index: Int, name: String) =
            NavHostFragment().apply {
                this.index = index
                this.name = name
            }

        private const val TAG = "NavHostFragment"
    }
}
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
        if (childFragmentManager.primaryNavigationFragment == null) {
            childFragmentManager.commitNow {
                val fragment = MultipleStackChildFragment.newInstance(name, 1)
                setPrimaryNavigationFragment(fragment)
                add(R.id.content, fragment)
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
            StackFragment().apply {
                this.index = index
                this.name = name
            }

        private const val TAG = "StackFragment"
    }
}
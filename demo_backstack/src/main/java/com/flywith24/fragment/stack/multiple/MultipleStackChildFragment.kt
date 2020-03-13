package com.flywith24.fragment.stack.multiple

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.commit
import com.flywith24.fragment.R
import com.flywith24.fragment.databinding.FragmentMultipleChildBinding
import com.flywith24.library.base.BaseFragment
import com.flywith24.library.base.ext.args

/**
 * @author yyz (杨云召)
 * @date   2020/3/5
 * time   22:36
 * description 多返回栈 子fragment
 */
class MultipleStackChildFragment :
    BaseFragment<FragmentMultipleChildBinding>(R.layout.fragment_multiple_child) {
    override fun initBinding(view: View): FragmentMultipleChildBinding =
        FragmentMultipleChildBinding.bind(view)


    val stableTag: String
        get() = "${javaClass.simpleName}-$name-$depth"
    private var name: String by args()
    var depth: Int by args()

    override fun init(savedInstanceState: Bundle?) {

        binding.text.text = getString(R.string.fragmentHint, name, depth.toString())
        binding.text.setOnClickListener {
            parentFragmentManager.commit {
                addToBackStack(name)
                replace(R.id.content, newInstance(name, depth + 1))
            }
        }
        Log.i("yyz11", "$name child ${parentFragmentManager.backStackEntryCount}")
    }

    companion object {
        fun newInstance(name: String, depth: Int): MultipleStackChildFragment =
            MultipleStackChildFragment().apply {
                this.name = name
                this.depth = depth
            }
    }
}
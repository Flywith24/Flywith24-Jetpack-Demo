package com.flywith24.fragment.stack.independent

import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import com.flywith24.fragment.R
import com.flywith24.fragment.databinding.FragmentChildBinding
import com.flywith24.library.base.BaseFragment
import com.flywith24.library.base.ext.args

/**
 * @author yyz (杨云召)
 * @date   2020/3/4
 * time   10:16
 * description 独立返回栈 子fragment
 * childFragment 显示stack名字及fragment个数,
 * fragmentManager 共用[IndependentStackParentFragment]的[getChildFragmentManager]
 */
class IndependentStackChildFragment : BaseFragment<FragmentChildBinding>(R.layout.fragment_child) {
    override fun initBinding(view: View): FragmentChildBinding = FragmentChildBinding.bind(view)

    val stableTag: String
        get() = "${javaClass.simpleName}-$name-$depth"

    private var name: String by args()

    var depth: Int by args()

    override fun init(savedInstanceState: Bundle?) {

        binding.button.text = getString(R.string.fragmentHint, name, depth)
        binding.button.setOnClickListener {
            parentFragmentManager.commit {
                replace(
                    id,
                    newInstance(
                        name,
                        depth + 1
                    )
                )
                addToBackStack(id.toString())
            }
        }
    }

    companion object {
        fun newInstance(name: String, depth: Int): IndependentStackChildFragment =
            IndependentStackChildFragment().apply {
                this.name = name
                this.depth = depth
            }
    }
}
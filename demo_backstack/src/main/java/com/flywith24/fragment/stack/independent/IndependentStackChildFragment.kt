package com.flywith24.fragment.stack.independent

import android.os.Bundle
import android.view.View
import android.widget.Toast
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

        binding.button.text = getString(R.string.fragmentHint, getValue(), getCount(depth))
        binding.button.setOnClickListener {
            if (depth < 13) {
                parentFragmentManager.commit {
                    replace(
                        id,
                        newInstance(
                            name,
                            depth + 1
                        )
                    )
                    addToBackStack(getValue())
                }
            } else {
                Toast.makeText(
                        requireContext(),
                        getString(R.string.done, getValue()),
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
        }
    }

    private fun getCount(value: Int): String = when (value) {
        11 -> "J"
        12 -> "Q"
        13 -> "K"
        1 -> "A"
        else -> value.toString()
    }

    private fun getValue(): String = when (id) {
        R.id.stack_1 -> "♥"
        R.id.stack_2 -> "♦"
        R.id.stack_3 -> "♠"
        R.id.stack_4 -> "♣"
        else -> ""
    }

    companion object {
        fun newInstance(name: String, depth: Int): IndependentStackChildFragment =
            IndependentStackChildFragment().apply {
                this.name = name
                this.depth = depth
            }
    }
}
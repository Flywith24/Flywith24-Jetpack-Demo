package com.flywith24.fragment.stack.single

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.commit
import com.flywith24.fragment.R
import com.flywith24.fragment.databinding.FragmentSingleChildBinding
import com.flywith24.fragment.stack.common.BaseStackFragment
import com.flywith24.library.base.ext.args

/**
 * @author yyz (杨云召)
 * @date   2020/3/4
 * time   10:16
 * description 独立返回栈 子fragment
 * childFragment 显示stack名字及fragment个数,
 * fragmentManager 共用[SingleStackParentFragment]的[getChildFragmentManager]
 */
class SingleStackChildFragment :
    BaseStackFragment<FragmentSingleChildBinding>(R.layout.fragment_single_child) {
    override fun initBinding(view: View): FragmentSingleChildBinding =
        FragmentSingleChildBinding.bind(view)

    val stableTag: String
        get() = "${javaClass.simpleName}-$name-$depth"

    private var name: String by args()

    var depth: Int by args()

    override fun init(savedInstanceState: Bundle?) {

        binding.button.text = getString(R.string.fragmentHint, name(id), getCount(depth))
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
                    addToBackStack(name(id))
                }
            } else {
                Toast.makeText(
                        requireContext(),
                        getString(R.string.done, name(id)),
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
        }
    }


    companion object {
        fun newInstance(name: String, depth: Int): SingleStackChildFragment =
            SingleStackChildFragment().apply {
                this.name = name
                this.depth = depth
            }
    }
}
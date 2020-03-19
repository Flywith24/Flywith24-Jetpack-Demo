package com.flywith24.demo_savestate.replacetest

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.flywith24.demo_savestate.R
import com.flywith24.demo_savestate.databinding.FragentFirstBinding
import com.flywith24.library.base.BaseFragment

/**
 * @author yyz(杨云召)
 * @date   2020/3/17
 * time   14:51
 * description
 * 测试 replace 状态保存
 * 1. 在 EditText 内输入内容， 输入的内容会存在 [label] 中并赋值给 TextView
 * 2. 点击 click replace 该 fragment 测试
 * 3. 旋转屏幕测试
 * 结果：
 * 步骤2 返回该 fragment 后 EditText 和 TextView 均恢复到之前的状态，[label] 有值
 * 步骤3 旋转屏幕后 EditText 和 TextView 均恢复到之前的状态，[label] 无值
 *
 */
class ParentFragment : BaseFragment<FragentFirstBinding>(R.layout.fragent_first) {
    override fun initBinding(view: View): FragentFirstBinding = FragentFirstBinding.bind(view)
    var label = ""

    override fun init(savedInstanceState: Bundle?) {
        //打印成员状态
        Log.i(TAG, "init: $label")

        binding.edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //为成员赋值
                label = s.toString()
                binding.text.text = label
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
        binding.button.setOnClickListener {
            parentFragmentManager.commit {
                addToBackStack(null)
                replace<ChildFragment>(
                    R.id.container
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG, "onDestroyView: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach: ")
    }

    companion object {
        private const val TAG = "HostFragment"
    }
}

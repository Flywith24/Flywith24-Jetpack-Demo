package com.flywith24.demo_savestate

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.flywith24.demo_savestate.databinding.FragentFirstBinding
import com.flywith24.library.base.BaseFragment

class FirstFragment : BaseFragment<FragentFirstBinding>(R.layout.fragent_first) {
    override fun initBinding(view: View): FragentFirstBinding = FragentFirstBinding.bind(view)
    var label = ""

    override fun init(savedInstanceState: Bundle?) {
        Log.i(TAG, "init: $label")
        binding.edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
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
                replace<ChildFragment>(R.id.container)
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

package com.flywith24.fragment.stack

import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.flywith24.fragment.R
import com.flywith24.fragment.databinding.FragmentHostBinding
import com.flywith24.fragment.stack.multiple.MultipleStackParentFragment
import com.flywith24.fragment.stack.single.SigleStackParentFragment
import com.flywith24.library.base.BaseFragment


class HostFragment : BaseFragment<FragmentHostBinding>(R.layout.fragment_host) {
    override fun initBinding(view: View): FragmentHostBinding = FragmentHostBinding.bind(view)

    override fun init(savedInstanceState: Bundle?) {
        binding.independentClick.setOnClickListener {
            parentFragmentManager.commit {
                addToBackStack(null)
                replace<SigleStackParentFragment>(R.id.content)
            }
        }
        binding.multipleClick.setOnClickListener {
            //todo 待完成
            parentFragmentManager.commit {
                addToBackStack(null)
                replace<MultipleStackParentFragment>(R.id.content)
            }
        }
    }
}

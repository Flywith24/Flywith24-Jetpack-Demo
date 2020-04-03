package com.flywith24.demo_databinding.binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.flywith24.demo_databinding.MyViewModel
import com.flywith24.demo_databinding.R
import com.flywith24.demo_databinding.databinding.FragmentDataBindingBinding

/**
 * @author yyz (杨云召)
 * @date   2020/4/3
 * time   13:59
 * description
 */
class DataBindingFragment : Fragment() {
    private val mViewModel by activityViewModels<MyViewModel>()

    private lateinit var mBinding: FragmentDataBindingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate<FragmentDataBindingBinding>(
            inflater,
            R.layout.fragment_data_binding,
            container,
            false
        )
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding.vm = mViewModel
        mBinding.lifecycleOwner = viewLifecycleOwner
    }
}
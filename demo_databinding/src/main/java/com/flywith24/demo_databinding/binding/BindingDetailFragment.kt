package com.flywith24.demo_databinding.binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.flywith24.demo_databinding.R
import com.flywith24.demo_databinding.databinding.FragmentBindingDetailBinding

/**
 * @author yyz (杨云召)
 * @date   2020/4/3
 * time   14:39
 * description
 */
class BindingDetailFragment : Fragment(R.layout.fragment_binding_detail) {
    private val mViewModel by activityViewModels<BindingViewModel>()
    private lateinit var mBinding: FragmentBindingDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_binding_detail,
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
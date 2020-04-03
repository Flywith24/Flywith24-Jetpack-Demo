package com.flywith24.demo_databinding.normal

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.flywith24.demo_databinding.R
import kotlinx.android.synthetic.main.fragment_normal_detail.*

/**
 * @author yyz (杨云召)
 * @date   2020/4/3
 * time   14:56
 * description
 */
class NormalDetailFragment : Fragment(R.layout.fragment_normal_detail) {
    private val mViewModel by activityViewModels<NormalViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewModel.firstName.observe(viewLifecycleOwner) {
            tvFirstName.text = it
        }
        mViewModel.lastName.observe(viewLifecycleOwner) {
            tvLastName.text = it
        }
    }
}
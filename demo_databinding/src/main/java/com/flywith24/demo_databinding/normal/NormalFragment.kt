package com.flywith24.demo_databinding.normal

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.flywith24.demo_databinding.R
import kotlinx.android.synthetic.main.fragment_normal.*

/**
 * @author yyz (杨云召)
 * @date   2020/4/3
 * time   14:19
 * description
 */
class NormalFragment : Fragment(R.layout.fragment_normal) {
    private val mViewModel by activityViewModels<NormalViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        etFirstName.addTextChangedListener {
            mViewModel.firstName.value = it.toString()
        }
        etLastName.addTextChangedListener {
            mViewModel.lastName.value = it.toString()
        }
    }
}
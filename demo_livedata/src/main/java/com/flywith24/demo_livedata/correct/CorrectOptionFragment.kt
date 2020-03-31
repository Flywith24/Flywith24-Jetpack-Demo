package com.flywith24.demo_livedata.correct

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.flywith24.demo_livedata.R
import kotlinx.android.synthetic.main.fragment_option.*

/**
 * @author yyz (杨云召)
 * @date   2020/3/31
 * time   10:44
 * description
 * 正确示例
 */
class CorrectOptionFragment : Fragment(R.layout.fragment_option) {
    private val mViewModel by viewModels<CorrectViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewModel.correctRandomNumber.observe(viewLifecycleOwner, Observer {
            text.text = it.toString()
            Toast.makeText(requireContext(), "正确示例值发生变化 $it", Toast.LENGTH_SHORT).show()
        })
        button.apply {
            text = "正确示例"
            setOnClickListener {
                mViewModel.onGetNumber()
            }
        }
    }
}
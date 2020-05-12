package com.flywith24.demo_resultapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import kotlinx.android.synthetic.main.fragment_common.*

/**
 * @author yyz (杨云召)
 * @date   2020/4/30
 * time   9:51
 * description
 */
class FragmentB : Fragment(R.layout.fragment_common) {

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        root.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.colorYellow
            )
        )
        text.text = FragmentB::class.simpleName
        button.setOnClickListener {

            setFragmentResult(
                REQUEST_KEY2, bundleOf(
                    BUNDLE_KEY1 to "result",
                    BUNDLE_KEY2 to "来自 FragmentB"
                )
            )

        }
        setFragmentResultListener(REQUEST_KEY1) { key, bundle ->
            text.text = "${bundle.getString(BUNDLE_KEY1)}，${bundle.getString(
                BUNDLE_KEY2
            )}"
        }
    }

}
package com.flywith24.demo_viewpager2

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment

/**
 * @author yyz(杨云召)
 * @date   2020/3/10
 * time   15:20
 * description
 */
class ItemFragment : Fragment(R.layout.fragment_item) {
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<AppCompatTextView>(R.id.text).text = param1
    }

    companion object {
        private const val ARG_PARAM1 = "param1"

        fun newInstance(param1: String) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)

                }
            }
    }
}

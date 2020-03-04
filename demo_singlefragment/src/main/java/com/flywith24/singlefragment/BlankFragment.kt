package com.flywith24.singlefragment

import androidx.fragment.app.Fragment

class BlankFragment : Fragment(R.layout.fragment_blank) {
    companion object {
        @JvmStatic
        fun newInstance() = BlankFragment()
    }
}

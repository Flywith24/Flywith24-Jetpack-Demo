package com.flywith24.singlefragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class BlankFragment : Fragment(R.layout.fragment_blank) {
    override fun onAttach(context: Context) {
        Log.i(TAG, "BlankFragment onAttach")
        Log.i(TAG, "BlankFragment parentFragmentManager $parentFragmentManager")
        Log.i(TAG, "BlankFragment childFragmentManager $childFragmentManager")

        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "BlankFragment onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.i(TAG, "BlankFragment onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "BlankFragment onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(TAG, "BlankFragment onViewCreated")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        Log.i(TAG, "BlankFragment onStart")
        super.onStart()
    }

    override fun onPause() {
        Log.i(TAG, "BlankFragment onPause")
        super.onPause()
    }

    override fun onResume() {
        Log.i(TAG, "BlankFragment onResume")
        super.onResume()
    }

    override fun onStop() {
        Log.i(TAG, "BlankFragment onStop")
        super.onStop()
    }

    override fun onDetach() {
        Log.i(TAG, "BlankFragment onDetach")
        super.onDetach()
    }

    override fun onDestroyView() {
        Log.i(TAG, "BlankFragment onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.i(TAG, "BlankFragment onDestroy")
        super.onDestroy()
    }

    companion object {
        const val TAG = "FragmentManager"

        @JvmStatic
        fun newInstance() = BlankFragment()
    }
}

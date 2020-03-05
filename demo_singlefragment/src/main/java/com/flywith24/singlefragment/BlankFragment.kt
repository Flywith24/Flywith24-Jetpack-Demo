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
        super.onAttach(context)
        Log.i(TAG, "BlankFragment onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "BlankFragment onCreate")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i(TAG, "BlankFragment onActivityCreated")
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
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "BlankFragment onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "BlankFragment onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "BlankFragment onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "BlankFragment onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "BlankFragment onStop")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i(TAG, "BlankFragment onDetach")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG, "BlankFragment onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "BlankFragment onDestroy")
    }

    companion object {
        const val TAG = "FragmentManager"

        @JvmStatic
        fun newInstance() = BlankFragment()
    }
}

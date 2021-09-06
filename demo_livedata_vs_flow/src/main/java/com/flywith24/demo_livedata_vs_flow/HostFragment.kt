package com.flywith24.demo_livedata_vs_flow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.flywith24.demo_livedata_vs_flow.livedata.LiveDataFragment
import com.flywith24.demo_livedata_vs_flow.sharedflow.SharedFlowFragment
import com.flywith24.demo_livedata_vs_flow.stateflow.StateFlowFragment

class HostFragment : Fragment(R.layout.fragment_host) {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    view.findViewById<View>(R.id.liveDataClick).setOnClickListener { liveDataClick(it) }
    view.findViewById<View>(R.id.stateFlowClick).setOnClickListener { stateFlowClick(it) }
    view.findViewById<View>(R.id.sharedFlowClick).setOnClickListener { shareFlowClick(it) }
  }

  private fun liveDataClick(v: View) = parentFragmentManager.commit {
    addToBackStack(null)
    replace<LiveDataFragment>(R.id.container)
  }


  private fun stateFlowClick(v: View) = parentFragmentManager.commit {
    addToBackStack(null)
    replace<StateFlowFragment>(R.id.container)
  }

  private fun shareFlowClick(v: View) = parentFragmentManager.commit {
    addToBackStack(null)
    replace<SharedFlowFragment>(R.id.container)
  }

}
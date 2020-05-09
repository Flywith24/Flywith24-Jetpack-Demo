package com.flywith24.demo_paging

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.flywith24.demo_paging.network.NetworkFragment
import kotlinx.android.synthetic.main.fragment_host.*

/**
 * @author yyz (杨云召)
 * @date   2020/5/9
 * time   10:55
 * description
 */
class HostFragment : Fragment(R.layout.fragment_host) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        network.setOnClickListener {
            parentFragmentManager.commit {
                addToBackStack(null)
                replace<NetworkFragment>(R.id.container)
            }
        }
        network_db.setOnClickListener {
            parentFragmentManager.commit {
                addToBackStack(null)
                replace<NetworkFragment>(R.id.container)
            }
        }
    }
}
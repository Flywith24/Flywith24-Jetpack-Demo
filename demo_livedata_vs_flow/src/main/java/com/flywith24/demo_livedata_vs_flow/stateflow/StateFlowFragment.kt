package com.flywith24.demo_livedata_vs_flow.stateflow

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.flywith24.demo_livedata_vs_flow.CommonFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StateFlowFragment : CommonFragment("StateFlow") {
  private val viewModel by viewModels<StateFlowViewModel>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    lifecycleScope.launch {
      edit.addTextChangedListener {
        viewModel.username.value = it?.toString() ?: ""
      }
    }

    lifecycleScope.launch {
      viewModel.headerText
          .flowWithLifecycle(lifecycle)
          .collect {
            header.text = it
          }
    }

    launchAndRepeatWithViewLifecycle {
      viewModel.showSnackbar.collect { showSnackbar(it) }
    }

    update.setOnClickListener {
      closeKeyboard(edit)
      viewModel.updateHeaderClick()
    }
    showSnackbar.setOnClickListener {
      viewModel.showSnackbarClick()
    }
  }
}
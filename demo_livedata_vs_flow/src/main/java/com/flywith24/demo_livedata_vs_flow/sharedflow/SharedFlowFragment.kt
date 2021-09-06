package com.flywith24.demo_livedata_vs_flow.sharedflow

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.flywith24.demo_livedata_vs_flow.CommonFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SharedFlowFragment : CommonFragment("SharedFlow") {
  private val viewModel by viewModels<SharedFlowViewModel>()

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
      lifecycleScope.launch {
        viewModel.updateHeaderClick()
      }
    }
    showSnackbar.setOnClickListener {
      lifecycleScope.launch {
        viewModel.showSnackbarClick()
      }
    }
  }
}
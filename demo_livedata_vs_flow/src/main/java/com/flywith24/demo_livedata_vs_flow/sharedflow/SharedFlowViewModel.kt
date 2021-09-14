package com.flywith24.demo_livedata_vs_flow.sharedflow

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow

class SharedFlowViewModel : ViewModel() {
  val username = MutableStateFlow("")

  private val _headerText = MutableSharedFlow<String>()
  val headerText: SharedFlow<String> = _headerText

  private val _showSnackbar = MutableSharedFlow<String>()
  val showSnackbar: SharedFlow<String> = _showSnackbar

  fun updateHeaderClick() {
    _headerText.tryEmit(username.value)
  }

  fun showSnackbarClick() {
    _showSnackbar.tryEmit(username.value)
  }
}
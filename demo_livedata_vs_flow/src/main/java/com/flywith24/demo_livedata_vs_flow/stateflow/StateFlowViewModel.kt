package com.flywith24.demo_livedata_vs_flow.stateflow

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class StateFlowViewModel : ViewModel() {
  val username = MutableStateFlow("")

  private val _headerText = MutableStateFlow("Header")
  val headerText = _headerText.asStateFlow()

  private val _showSnackbar = MutableStateFlow<String>("default")
  val showSnackbar: StateFlow<String> = _showSnackbar


  fun updateHeaderClick() {
    _headerText.value = username.value
  }

  fun showSnackbarClick() {
    _showSnackbar.value = username.value
  }
}
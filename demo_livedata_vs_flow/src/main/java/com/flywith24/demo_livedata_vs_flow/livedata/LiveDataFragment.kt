package com.flywith24.demo_livedata_vs_flow.livedata

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.flywith24.demo_livedata_vs_flow.CommonFragment


class LiveDataFragment : CommonFragment("LiveData") {

  private val viewModel by viewModels<LiveDataViewModel>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    edit.addTextChangedListener { viewModel.username.value = it?.toString() }

    viewModel.headerText.observe(viewLifecycleOwner) { header.text = it }
    viewModel.showSnackbar.observe(viewLifecycleOwner) { showSnackbar(it) }

    update.setOnClickListener {
      closeKeyboard(edit)
      viewModel.updateHeaderClick()
    }
    showSnackbar.setOnClickListener {
      viewModel.showSnackBarClick()
    }
  }
}
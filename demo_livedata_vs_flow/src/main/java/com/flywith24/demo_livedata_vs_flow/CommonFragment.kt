package com.flywith24.demo_livedata_vs_flow

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class CommonFragment(private val title: String) : Fragment(R.layout.fragment_common) {
  lateinit var header: TextView
  lateinit var edit: TextView
  lateinit var showSnackbar: Button
  lateinit var update: Button

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
    toolbar.title = title
    header = view.findViewById(R.id.header)
    edit = view.findViewById(R.id.edit)
    showSnackbar = view.findViewById(R.id.showSnackbar)
    update = view.findViewById(R.id.update)
  }

  fun showSnackbar(text: String) {
    Snackbar.make(showSnackbar, text, Snackbar.LENGTH_SHORT).show()
  }

  internal fun closeKeyboard(view: View) {
    val manager =
        ContextCompat.getSystemService(requireContext(), InputMethodManager::class.java)
    manager?.hideSoftInputFromWindow(view.windowToken, 0)
  }

  inline fun Fragment.launchAndRepeatWithViewLifecycle(
      minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
      crossinline block: suspend CoroutineScope.() -> Unit
  ) {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
        block()
      }
    }
  }
}
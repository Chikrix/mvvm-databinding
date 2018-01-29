package com.tutor.proteins.placetalk.util

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast

/**
 *
 */

fun EditText.setTextChangeAction(action: (CharSequence) -> Unit) {
  this.addTextChangedListener(object : TextWatcher {
    override fun afterTextChanged(s: Editable) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
      action(s)
    }
  })
}

fun Context.showToast(messageResId: Int, length: Int = Toast.LENGTH_SHORT) {
  Toast.makeText(this, getString(messageResId), length).show()
}

package com.saganize.solwave.sample.core.util

import android.content.Context
import android.widget.Toast

fun Context.showToast(message: String) {
    message.ifBlank { return }
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
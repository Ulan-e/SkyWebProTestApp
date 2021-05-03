package com.ulanapp.skywebprotestapp.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showMessage(message: String, duration: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, message, duration).show()
}
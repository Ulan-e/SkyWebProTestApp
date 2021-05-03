package com.ulanapp.skywebprotestapp.presentation.main

import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import com.ulanapp.skywebprotestapp.R
import com.ulanapp.skywebprotestapp.presentation.main.MainActivity.Companion.IMAGES_FRAGMENT
import com.ulanapp.skywebprotestapp.presentation.main.MainActivity.Companion.LOGIN_FRAGMENT

class MainViewModel(
    private val listener: CallFragmentListener
) : ViewModel() {

    init {
        listener.showFragment(LOGIN_FRAGMENT)
    }

    // клик на айтем нижнего навигационного меню
    fun onNavigationClick(@NonNull menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.login -> {
                listener.showFragment(LOGIN_FRAGMENT)
                true
            }
            R.id.images -> {
                listener.showFragment(IMAGES_FRAGMENT)
                true
            }
            else -> false
        }
    }
}
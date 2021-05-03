package com.ulanapp.skywebprotestapp.presentation.main

import androidx.databinding.BindingAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ulanapp.skywebprotestapp.R

class BottomNavigationAdapter {

    companion object {

        @BindingAdapter("onItemSelected")
        @JvmStatic
        fun setNavigationClickListener(
            view: BottomNavigationView,
            listener: BottomNavigationView.OnNavigationItemSelectedListener
        ) {
            // по умолчания активный экран логина
            view.selectedItemId = R.id.login

            // ставим листенер
            view.setOnNavigationItemSelectedListener(listener)
        }
    }
}
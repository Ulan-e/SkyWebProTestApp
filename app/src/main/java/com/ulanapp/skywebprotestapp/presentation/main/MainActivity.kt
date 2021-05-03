package com.ulanapp.skywebprotestapp.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ulanapp.skywebprotestapp.R
import com.ulanapp.skywebprotestapp.databinding.ActivityMainBinding
import com.ulanapp.skywebprotestapp.presentation.base.BaseActivity
import com.ulanapp.skywebprotestapp.presentation.images.ImagesFragment
import com.ulanapp.skywebprotestapp.presentation.login.LoginFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), CallFragmentListener {

    private lateinit var model: MainViewModel
    private lateinit var binding: ActivityMainBinding

    companion object {
        const val LOGIN_FRAGMENT = "home_fragment"
        const val IMAGES_FRAGMENT = "images_fragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model = ViewModelProvider(this, MainViewModelFactory(this))
            .get(MainViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.model = model

        setSupportActionBar(main_toolbar)
    }

    override fun showFragment(fragmentName: String) {
        when (fragmentName) {
            LOGIN_FRAGMENT -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, LoginFragment())
                    .addToBackStack(null)
                    .commit()
            }
            IMAGES_FRAGMENT -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, ImagesFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
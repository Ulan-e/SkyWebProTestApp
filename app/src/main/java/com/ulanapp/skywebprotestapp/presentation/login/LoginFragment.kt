package com.ulanapp.skywebprotestapp.presentation.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ulanapp.skywebprotestapp.databinding.FragmentLoginBinding
import com.ulanapp.skywebprotestapp.domain.usecase.GetWeatherUseCase
import com.ulanapp.skywebprotestapp.presentation.main.MainActivity
import com.ulanapp.skywebprotestapp.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    @Inject
    lateinit var getWeatherUseCase: GetWeatherUseCase

    private lateinit var binding: FragmentLoginBinding
    private lateinit var model: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.title = "Логин"

        model = ViewModelProvider(
           this,
            LoginModelFactory(getWeatherUseCase)
        ).get(LoginViewModel::class.java)
        binding.model = this.model

        btn_login.setOnClickListener {
            model.getWeatherInfo(498817)
            model.data.observe(activity!!, Observer {
                Log.d("iamuli", it.toString())
            })
        }

        model.loadingProgress.observe(activity!!, Observer { progress ->
            progress_bar.visibility = if (progress == true)
                View.VISIBLE
            else
                View.GONE
        })

        model.errorMessage.observe(activity!!, Observer { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                Log.d("iamuli", errorMessage.toString())
                Toast.makeText(activity!!, "Error loading data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
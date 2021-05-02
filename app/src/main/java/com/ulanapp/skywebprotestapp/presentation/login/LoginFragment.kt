package com.ulanapp.skywebprotestapp.presentation.login

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ulanapp.skywebprotestapp.R
import com.ulanapp.skywebprotestapp.databinding.FragmentLoginBinding
import com.ulanapp.skywebprotestapp.domain.usecase.GetWeatherUseCase
import com.ulanapp.skywebprotestapp.presentation.base.BaseFragment
import com.ulanapp.skywebprotestapp.presentation.main.MainActivity
import com.ulanapp.skywebprotestapp.utils.PASSWORD_PATTERN
import com.ulanapp.skywebprotestapp.utils.showMessage
import kotlinx.android.synthetic.main.fragment_login.*
import timber.log.Timber
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    @Inject
    lateinit var weatherUseCase: GetWeatherUseCase

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

        (activity as MainActivity).supportActionBar?.title = resources.getString(R.string.login)

        model = ViewModelProvider(this, LoginModelFactory(weatherUseCase))
            .get(LoginViewModel::class.java)
        binding.model = this.model

        btn_login.setOnClickListener {
            loadWeatherInfo()
        }

        getWeatherData()

        getLoadingProgress()

        getErrorMessage()
    }

    // получаем данные о погоде
    private fun getWeatherData() {
        model.errorMessage.observe(this, { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                binding.root.showMessage(resources.getString(R.string.error_network))
                Timber.e(errorMessage)
            }
        })
    }

    // получаем прогресс загрузки данныхо погоде
    private fun getLoadingProgress() {
        model.loadingProgress.observe(this, { progress ->
            progress_bar.visibility = if (progress == true)
                View.VISIBLE
            else
                View.GONE
        })
    }

    // получаем ошибку от сервера
    private fun getErrorMessage() {
        model.data.observe(this, {
            val message = String.format(
                resources.getString(R.string.message),
                it.name,
                it.main.tempMin,
                it.clouds.all,
                it.main.humidity
            )
            binding.root.showMessage(message)
            Timber.e(it.toString())
        })
    }

    // загрузка сведений о погоде
    private fun loadWeatherInfo() {
        val cityId = resources.getInteger(R.integer.cityId)
        val apiKey = resources.getString(R.string.api_key)
        val lang = resources.getString(R.string.lang)
        val units = resources.getString(R.string.units_metric)

        if (!isValidLogin() or !isValidPassword()) {
            return
        }
        model.getWeatherInfo(cityId, apiKey, lang, units)
    }

    // проверка логина
    private fun isValidLogin(): Boolean {
        val loginInput: String = textFieldLogin.editText?.text.toString().trim()
        return if (loginInput.isEmpty()) {
            textFieldLogin.error = resources.getString(R.string.enter_login)
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(loginInput).matches()) {
            textFieldLogin.error = resources.getString(R.string.incorrect_login)
            false
        } else {
            textFieldLogin.error = null
            true
        }
    }

    // проверка пароля
    private fun isValidPassword(): Boolean {
        val passwordInput: String = textFieldPassword.editText?.text.toString().trim()
        return if (passwordInput.isEmpty()) {
            textFieldPassword.error = resources.getString(R.string.enter_password)
            false
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            textFieldPassword.error = resources.getString(R.string.incorrect_password)
            false
        } else {
            textFieldPassword.error = null
            true
        }
    }
}
package com.nandaiqbalh.kancaumkm.presentation.ui.auth.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.nandaiqbalh.kancaumkm.R
import com.nandaiqbalh.kancaumkm.databinding.ActivityLoginBinding
import com.nandaiqbalh.kancaumkm.presentation.ui.auth.register.RegisterActivity
import com.nandaiqbalh.kancaumkm.presentation.ui.home.pembeli.PembeliActivity
import dagger.hilt.android.AndroidEntryPoint


@Suppress("DEPRECATION")
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

	private var _binding: ActivityLoginBinding? = null
	private val binding get() = _binding!!

	private val viewModel: LoginViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		_binding = ActivityLoginBinding.inflate(layoutInflater)
		setContentView(binding.root)

		isAlreadyLogin()

		buttonClickListener()

		playAnimation()
	}

	private fun isAlreadyLogin() {
		viewModel.getStatusLogin().observe(this@LoginActivity) { isOnboardingCompleted ->
			if (isOnboardingCompleted == true) {
				val intent = Intent(this@LoginActivity, PembeliActivity::class.java)
				startActivity(intent)
				finishAffinity()
			}

		}
	}


	private fun buttonClickListener() {
		binding.btnDaftar.setOnClickListener {
			val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
			startActivity(intent)
			finishAffinity()
		}

		binding.btnLogin.setOnClickListener {
			setLoading(true)

			if (validateForm()) {
				val enteredEmail = binding.edLoginEmail.text.toString().trim()
				val enteredPassword = binding.edLoginPassword.text.toString().trim()

				viewModel.getSavedCredential().observe(this@LoginActivity) { savedCredentials ->
					val savedEmail = savedCredentials.first ?: ""
					val savedPassword = savedCredentials.second ?: ""

					// Menggunakan Handler untuk menambahkan delay 2 detik
					Handler().postDelayed({

						if (enteredEmail == savedEmail && enteredPassword == savedPassword) {
							val intent = Intent(this@LoginActivity, PembeliActivity::class.java)
							startActivity(intent)
							finishAffinity()

							viewModel.setStatusLogin(true)

							setLoading(false)
						} else {
							Toast.makeText(
								this@LoginActivity,
								"Invalid credentials",
								Toast.LENGTH_SHORT
							).show()

							setLoading(false)
						}

					}, 2000) // Delay 2 detik (2000 milidetik)
				}
			}
		}

	}

	private fun validateForm(): Boolean {
		val email = binding.edLoginEmail.text.toString()
		val password = binding.edLoginPassword.text.toString()

		var isFormValid = true

		if (email.isEmpty()) {
			isFormValid = false
			binding.tilLoginEmail.error = getString(R.string.tv_error_input_blank)
		} else {
			binding.tilLoginEmail.error = null
		}

		if (password.isEmpty()) {
			isFormValid = false
			binding.tilLoginPassword.error = getString(R.string.tv_error_input_blank)
		} else {
			binding.tilLoginPassword.error = null
		}

		return isFormValid
	}

	private fun setLoading(isLoading: Boolean) {
		binding.pbLogin.isVisible = isLoading
	}

	private fun playAnimation() {

		val logo = ObjectAnimator.ofFloat(binding.ivLogoSmall, View.ALPHA, 1f).setDuration(500)
		val tvTitle = ObjectAnimator.ofFloat(binding.tvLoginTitle, View.ALPHA, 1f).setDuration(500)
		val tvMessage =
			ObjectAnimator.ofFloat(binding.tvLoginSubtitle, View.ALPHA, 1f).setDuration(500)
		val tilEmail =
			ObjectAnimator.ofFloat(binding.tilLoginEmail, View.ALPHA, 1f).setDuration(500)
		val tilPassword =
			ObjectAnimator.ofFloat(binding.tilLoginPassword, View.ALPHA, 1f).setDuration(500)
		val buttonLogin = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(500)
		val llLoginAlternate =
			ObjectAnimator.ofFloat(binding.llLoginAlternate, View.ALPHA, 1f).setDuration(500)


		val together = AnimatorSet().apply {
			playTogether(tvTitle, tvMessage)
		}

		AnimatorSet().apply {
			playSequentially(
				logo,
				tilEmail,
				tilPassword,
				buttonLogin,
				llLoginAlternate,
				together
			)
			startDelay = 500
		}.start()
	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}
}
package com.nandaiqbalh.kancaumkm.presentation.ui.auth.register

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.nandaiqbalh.kancaumkm.R
import com.nandaiqbalh.kancaumkm.databinding.ActivityRegisterBinding
import com.nandaiqbalh.kancaumkm.presentation.ui.auth.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

	private var _binding: ActivityRegisterBinding? = null
	private val binding get() = _binding!!

	private val viewModel:RegisterViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		_binding = ActivityRegisterBinding.inflate(layoutInflater)
		setContentView(binding.root)

		buttonClickListener()

	}

	private fun buttonClickListener(){
		binding.btnLogin.setOnClickListener {
			val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
			startActivity(intent)
			finishAffinity()
		}

		binding.btnDaftar.setOnClickListener {

			setLoading(true)

			val email = binding.edRegisterEmail.text.toString().trim()
			val password = binding.edRegisterPassword.text.toString().trim()

			// Menggunakan Handler untuk menambahkan delay 2 detik sebelum mendapatkan respon
			Handler().postDelayed({

				if (validateForm()) {
					viewModel.saveCredential(email, password)

					// Setelah delay, Anda dapat melanjutkan dengan menampilkan pesan dan berpindah ke aktivitas login
					Toast.makeText(this, "Pendaftaran berhasil!", Toast.LENGTH_SHORT).show()

					val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
					startActivity(intent)
					finishAffinity()
				} else {
					// Jika validasi tidak berhasil, tampilkan pesan tidak valid
					Toast.makeText(this, "Pendaftaran tidak valid!", Toast.LENGTH_SHORT).show()
				}

				setLoading(false)

			}, 2000) // Delay 2 detik (2000 milidetik)
		}

	}

	private fun validateForm(): Boolean {
		val email = binding.edRegisterEmail.text.toString()
		val password = binding.edRegisterPassword.text.toString()
		val confirmPassword = binding.edConfirmRegisterPassword.text.toString()

		var isFormValid = true

		if (email.isEmpty()) {
			isFormValid = false
			binding.tilRegisterEmail.error = getString(R.string.tv_error_input_blank)
		} else {
			binding.tilRegisterEmail.error = null
		}

		if (password.isEmpty()) {
			isFormValid = false
			binding.tilRegisterPassword.error = getString(R.string.tv_error_input_blank)
		} else {
			binding.tilRegisterPassword.error = null
		}

		if (confirmPassword.isEmpty()) {
			isFormValid = false
			binding.tilConfimrRegisterPassword.error = getString(R.string.tv_error_input_blank)
		} else if (password != confirmPassword) {
			isFormValid = false
			binding.tilConfimrRegisterPassword.error = getString(R.string.tv_error_password_mismatch)
		} else {
			binding.tilConfimrRegisterPassword.error = null
		}

		return isFormValid
	}


	private fun setLoading(isLoading: Boolean) {
		binding.pbRegister.isVisible = isLoading
	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}
}
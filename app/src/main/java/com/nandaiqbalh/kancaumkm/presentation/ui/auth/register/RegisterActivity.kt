package com.nandaiqbalh.kancaumkm.presentation.ui.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.nandaiqbalh.kancaumkm.R
import com.nandaiqbalh.kancaumkm.databinding.ActivityRegisterBinding
import com.nandaiqbalh.kancaumkm.presentation.ui.auth.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

	private var _binding: ActivityRegisterBinding? = null
	private val binding get() = _binding!!

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
	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}
}
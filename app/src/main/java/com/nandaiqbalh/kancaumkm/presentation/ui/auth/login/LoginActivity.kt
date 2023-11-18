package com.nandaiqbalh.kancaumkm.presentation.ui.auth.login

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.nandaiqbalh.kancaumkm.R
import com.nandaiqbalh.kancaumkm.databinding.ActivityLoginBinding
import com.nandaiqbalh.kancaumkm.presentation.ui.auth.register.RegisterActivity


class LoginActivity : AppCompatActivity() {

	private var _binding: ActivityLoginBinding? = null
	private val binding get() = _binding!!

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		_binding = ActivityLoginBinding.inflate(layoutInflater)
		setContentView(binding.root)

		buttonClickListener()

		playAnimation()
	}

	private fun buttonClickListener(){
		binding.btnDaftar.setOnClickListener {
			val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
			startActivity(intent)
			finishAffinity()
		}
	}

	private fun playAnimation() {

		val logo = ObjectAnimator.ofFloat(binding.ivLogoSmall, View.ALPHA, 1f).setDuration(500)
		val tvTitle = ObjectAnimator.ofFloat(binding.tvLoginTitle, View.ALPHA, 1f).setDuration(500)
		val tvMessage = ObjectAnimator.ofFloat(binding.tvLoginSubtitle, View.ALPHA, 1f).setDuration(500)
		val tilEmail = ObjectAnimator.ofFloat(binding.tilLoginEmail, View.ALPHA, 1f).setDuration(500)
		val tilPassword = ObjectAnimator.ofFloat(binding.tilLoginPassword, View.ALPHA, 1f).setDuration(500)
		val buttonLogin = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(500)
		val llLoginAlternate = ObjectAnimator.ofFloat(binding.llLoginAlternate, View.ALPHA, 1f).setDuration(500)


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
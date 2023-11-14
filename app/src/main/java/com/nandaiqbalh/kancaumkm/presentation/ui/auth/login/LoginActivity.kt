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

		setupSpinner()

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

	private fun setupSpinner(){
		val spinnerRole = findViewById<Spinner>(R.id.spinnerRole)

		val roles = arrayOf("Pembeli", "Pelaku UMKM")

		val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roles)

		spinnerRole.adapter = adapter
	}

	private fun playAnimation() {

		val logo = ObjectAnimator.ofFloat(binding.ivLogoSmall, View.ALPHA, 1f).setDuration(500)
		val tvTitle = ObjectAnimator.ofFloat(binding.tvLoginTitle, View.ALPHA, 1f).setDuration(500)
		val tvMessage = ObjectAnimator.ofFloat(binding.tvLoginSubtitle, View.ALPHA, 1f).setDuration(500)
		val tilEmail = ObjectAnimator.ofFloat(binding.tilLoginEmail, View.ALPHA, 1f).setDuration(500)
		val tilPassword = ObjectAnimator.ofFloat(binding.tilLoginPassword, View.ALPHA, 1f).setDuration(500)
		val buttonLogin = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(500)
		val llLoginAlternate = ObjectAnimator.ofFloat(binding.llLoginAlternate, View.ALPHA, 1f).setDuration(500)

		val spinnerRole = binding.spinnerRole
		val childViews = mutableListOf<Animator>()

		// Iterate over the child views of the Spinner and animate their alpha
		for (i in 0 until spinnerRole.childCount) {
			val child = spinnerRole.getChildAt(i)
			val animator = ObjectAnimator.ofFloat(child, View.ALPHA, 1f).setDuration(500)
			childViews.add(animator)
		}

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
			playTogether(childViews)  // Include child views in the animation set
			startDelay = 500
		}.start()
	}


	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}
}
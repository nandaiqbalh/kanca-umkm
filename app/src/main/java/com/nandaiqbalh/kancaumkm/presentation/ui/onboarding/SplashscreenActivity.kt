package com.nandaiqbalh.kancaumkm.presentation.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.nandaiqbalh.kancaumkm.databinding.ActivitySplashscreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Suppress("DEPRECATION")
class SplashscreenActivity : AppCompatActivity() {

	private var _binding: ActivitySplashscreenBinding? = null
	private val binding get() = _binding!!

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		_binding = ActivitySplashscreenBinding.inflate(layoutInflater)
		setContentView(binding.root)

		// Animasi zoom in untuk iv_app_logo
		val zoomInAnimationLogo = ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
		zoomInAnimationLogo.duration = 700 // Sesuaikan durasi animasi
		binding.ivAppLogo.startAnimation(zoomInAnimationLogo)
		binding.ivAppLogo.visibility = View.VISIBLE

		// Menambahkan delay sebelum pindah ke OnboardingActivity (disesuaikan dengan durasi animasi)
		zoomInAnimationLogo.setAnimationListener(object : Animation.AnimationListener {
			override fun onAnimationStart(animation: Animation?) {}

			override fun onAnimationEnd(animation: Animation?) {
				Handler().postDelayed({
					val intent = Intent(this@SplashscreenActivity, OnboardingActivity::class.java)
					startActivity(intent)
					finishAffinity()
				}, 2000)
			}

			override fun onAnimationRepeat(animation: Animation?) {}
		})
	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}
}

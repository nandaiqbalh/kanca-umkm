package com.nandaiqbalh.kancaumkm.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.nandaiqbalh.kancaumkm.MainActivity
import com.nandaiqbalh.kancaumkm.R
import com.nandaiqbalh.kancaumkm.databinding.ActivityOnboardingBinding
import com.nandaiqbalh.kancaumkm.presentation.ui.onboarding.carouselview.CarouselPage
import com.nandaiqbalh.kancaumkm.presentation.ui.onboarding.carouselview.CarouselPager
import com.nandaiqbalh.kancaumkm.presentation.ui.onboarding.carouselview.ZoomOutPageTransformer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity(), CarouselPager.CarouselListener {

	private val viewModel: OnboardingViewModel by viewModels()
	private lateinit var binding: ActivityOnboardingBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityOnboardingBinding.inflate(layoutInflater)
		setContentView(binding.root)

		isAlreadyOnboarding()

		val titles = resources.getStringArray(R.array.carousel_titles)
		val subtitles = resources.getStringArray(R.array.carousel_subtitles)
		val drawableIds = resources.obtainTypedArray(R.array.carousel_drawables)

		val carouselPages = mutableListOf<CarouselPage>()
		for (i in titles.indices) {
			val drawableId = drawableIds.getResourceId(i, 0)
			carouselPages.add(CarouselPage(drawableId, titles[i], subtitles[i]))
		}

		drawableIds.recycle()

		binding.carouselPager
			.setUpCarousel(this, carouselPages)
			.setUpPageTransformer(ZoomOutPageTransformer())
			.setUpCarouselListener(this)
	}

	private fun isAlreadyOnboarding() {
		lifecycleScope.launchWhenCreated {
			viewModel.getStatusOnboarding().observe(this@OnboardingActivity) { isOnboardingCompleted ->
				if (isOnboardingCompleted == true) {
					val intent = Intent(this@OnboardingActivity, MainActivity::class.java)
					startActivity(intent)
					finishAffinity()
				}
			}
		}
	}

	override fun onCarouselFinished(skipped: Boolean) {
		// set status on boarding
		viewModel.setStatusOnboarding(true)
	}
}
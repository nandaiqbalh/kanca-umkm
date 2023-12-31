package com.nandaiqbalh.kancaumkm.presentation.ui.home.pembeli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nandaiqbalh.kancaumkm.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PembeliActivity : AppCompatActivity() {
	private lateinit var navController: NavController
	private lateinit var bottomNav: BottomNavigationView

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		supportActionBar?.hide()
		setContentView(R.layout.activity_pembeli)

		val navHostFragment = supportFragmentManager
			.findFragmentById(R.id.fragmentContainerViewHome) as NavHostFragment

		navController = navHostFragment.navController

		bottomNav = findViewById(R.id.bottom_navigation_user)

		val appBarConfiguration = AppBarConfiguration.Builder(
			R.id.pembeliBerandaFragment,
			R.id.pembeliTokoPremiumFragment,
			R.id.pembeliRiwayatFragment,
			R.id.pembeliProfilFragment,

			).build()

		bottomNav.itemIconTintList = null

		setupActionBarWithNavController(navController, appBarConfiguration)
		bottomNav.setupWithNavController(navController)
//		navController.addOnDestinationChangedListener { _, destination, _ ->
//			when (destination.id) {
//				R.id.action_provinceFragment_to_cityFragment -> {
//					hideBottomNav(true)
//				}
//				R.id.action_cityFragment_to_provinceFragment -> {
//					hideBottomNav(true)
//				}
//				else -> hideBottomNav(false)
//			}
//		}
	}

	private fun hideBottomNav(hide: Boolean) {
		if (hide) {
			bottomNav.visibility = View.GONE
		} else {
			bottomNav.visibility = View.VISIBLE
		}
	}

	override fun onSupportNavigateUp(): Boolean {
		return navController.navigateUp() || super.onSupportNavigateUp()
	}
}
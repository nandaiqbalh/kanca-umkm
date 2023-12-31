package com.nandaiqbalh.kancaumkm.presentation.ui.home.pembeli.premium

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nandaiqbalh.kancaumkm.R
import com.nandaiqbalh.kancaumkm.databinding.FragmentPembeliTokoPremiumBinding


class PembeliTokoPremiumFragment : Fragment() {

	private var _binding: FragmentPembeliTokoPremiumBinding? = null
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View{
		_binding = FragmentPembeliTokoPremiumBinding.inflate(layoutInflater, container, false)
		return binding.root
	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}

}
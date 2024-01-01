package com.nandaiqbalh.kancaumkm.presentation.ui.home.pembeli.premium

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.nandaiqbalh.kancaumkm.R
import com.nandaiqbalh.kancaumkm.data.local.pembeli.tokopremium.model.TokoPremium
import com.nandaiqbalh.kancaumkm.databinding.FragmentPembeliTokoPremiumBinding
import com.nandaiqbalh.kancaumkm.presentation.ui.home.pembeli.beranda.ProductAdapter
import com.nandaiqbalh.kancaumkm.presentation.ui.home.pembeli.premium.adapter.PembeliTokoPremiumAdapter


class PembeliTokoPremiumFragment : Fragment() {

	private var _binding: FragmentPembeliTokoPremiumBinding? = null
	private val binding get() = _binding!!

	private val list = ArrayList<TokoPremium>()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		_binding = FragmentPembeliTokoPremiumBinding.inflate(layoutInflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		list.addAll(getTokoPremiumList())

		setRecyclerView()

	}

	private fun setRecyclerView() {

		val adapter = PembeliTokoPremiumAdapter()
		adapter.setList(list)

		binding.apply {
			rvProduct.layoutManager = LinearLayoutManager(
				requireContext(),
				LinearLayoutManager.VERTICAL,
				false
			)
			rvProduct.adapter = adapter
		}
	}

	private fun getTokoPremiumList(): ArrayList<TokoPremium> {
		val dataId = resources.getStringArray(R.array.id)
		val dataGambar = resources.obtainTypedArray(R.array.gambar_product)
		val dataNama = resources.getStringArray(R.array.namaProduk)
		val dataHarga = resources.getStringArray(R.array.hargaProduk)
		val dataLokasi = resources.getStringArray(R.array.lokasiProduk)


		val listTokoPremium = ArrayList<TokoPremium>()
		for (i in dataId.indices) {
			val tokoPremium = TokoPremium(
				idProduct = dataId[i].toInt(),
				gambarProduct = dataGambar.getResourceId(i, -1),
				namaProduct = dataNama[i].toString(),
				hargaProduct = dataHarga[i],
				lokasiTokoPremium = dataLokasi[i]
			)

			list.add(tokoPremium)
		}
		return listTokoPremium
	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}

}
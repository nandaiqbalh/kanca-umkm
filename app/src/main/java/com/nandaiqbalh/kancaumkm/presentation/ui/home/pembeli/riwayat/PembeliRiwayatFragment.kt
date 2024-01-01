package com.nandaiqbalh.kancaumkm.presentation.ui.home.pembeli.riwayat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.nandaiqbalh.kancaumkm.R
import com.nandaiqbalh.kancaumkm.data.local.pembeli.riwayat.model.RiwayatModel
import com.nandaiqbalh.kancaumkm.databinding.FragmentPembeliRiwayatBinding
import com.nandaiqbalh.kancaumkm.presentation.ui.home.pembeli.riwayat.adapter.PembeliRiwayatAdapter

class PembeliRiwayatFragment : Fragment() {

	private var _binding: FragmentPembeliRiwayatBinding? = null
	private val binding get() = _binding!!

	private val list = ArrayList<RiwayatModel>()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		_binding = FragmentPembeliRiwayatBinding.inflate(layoutInflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		list.addAll(getRiwayatList())

		setRecyclerView()

	}

	private fun setRecyclerView() {

		val adapter = PembeliRiwayatAdapter()
		adapter.setList(list)

		binding.apply {
			rvRiwayat.layoutManager = LinearLayoutManager(
				requireContext(),
				LinearLayoutManager.VERTICAL,
				false
			)
			rvRiwayat.adapter = adapter
		}
	}

	private fun getRiwayatList(): ArrayList<RiwayatModel> {
		val dataId = resources.getStringArray(R.array.id_riwayat)
		val dataGambar = resources.obtainTypedArray(R.array.gambar_product_riwayat)
		val dataNama = resources.getStringArray(R.array.namaProduk_riwayat)
		val dataHarga = resources.getStringArray(R.array.hargaProduk_riwayat)
		val dataTanggal = resources.getStringArray(R.array.tanggalProduk_riwayat)
		val dataStatus = resources.getStringArray(R.array.statusProduk_riwayat)
		val dataKuantitas = resources.getStringArray(R.array.kuantitasProduk_riwayat)
		val dataTotalHarga = resources.getStringArray(R.array.hargaProduk_riwayat)


		val listRiwayat = ArrayList<RiwayatModel>()
		for (i in dataId.indices) {
			val riwayat = RiwayatModel(
				idProduct = dataId[i].toInt(),
				gambarProduct = dataGambar.getResourceId(i, -1),
				namaProduct = dataNama[i].toString(),
				hargaProduct = dataHarga[i],
				statusBelanja = dataStatus[i],
				tanggalBelanja = dataTanggal[i],
				jumlahBarang = dataKuantitas[i],
				totalBelanja = dataTotalHarga[i],
				)

			list.add(riwayat)
		}
		return listRiwayat
	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}


}
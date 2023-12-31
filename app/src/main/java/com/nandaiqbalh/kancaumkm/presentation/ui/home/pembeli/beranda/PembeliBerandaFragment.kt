package com.nandaiqbalh.kancaumkm.presentation.ui.home.pembeli.beranda

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.nandaiqbalh.kancaumkm.R
import com.nandaiqbalh.kancaumkm.data.local.product.models.Product
import com.nandaiqbalh.kancaumkm.databinding.FragmentPembeliBerandaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PembeliBerandaFragment : Fragment() {

	private var _binding:FragmentPembeliBerandaBinding? = null
	private val binding get() = _binding!!

	private val list = ArrayList<Product>()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		// Inflate the layout for this fragment using view binding
		_binding = FragmentPembeliBerandaBinding.inflate(inflater, container, false)
		return binding.root


	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		list.addAll(getProdukList())

		setRecyclerView()

		actionButtonListener()
	}

	private fun actionButtonListener() {

	}

	private fun setRecyclerView() {
		binding.rvProduct.setNestedScrollingEnabled(false);
		binding.rvProduct.layoutManager =
			GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false) // 2 items per row
		binding.rvProduct.adapter = ProductAdapter(list)
	}

	@SuppressLint("Recycle")
	private fun getProdukList(): ArrayList<Product> {
		val dataId = resources.getStringArray(R.array.id)
		val dataGambar = resources.obtainTypedArray(R.array.gambar_product)


		val listProduct = ArrayList<Product>()
		for (i in dataId.indices) {
			val product = Product(
				idProduct = dataId[i].toInt(),
				gambarProduct = dataGambar.getResourceId(i, -1),
			)

			listProduct.add(product)
		}
		return listProduct
	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}

}
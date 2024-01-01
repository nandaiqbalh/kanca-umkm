package com.nandaiqbalh.kancaumkm.presentation.ui.home.pembeli.riwayat.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nandaiqbalh.kancaumkm.R
import com.nandaiqbalh.kancaumkm.data.local.pembeli.riwayat.model.RiwayatModel
import com.nandaiqbalh.kancaumkm.data.local.pembeli.tokopremium.model.TokoPremium
import com.nandaiqbalh.kancaumkm.databinding.ItemRiwayatBinding
import com.nandaiqbalh.kancaumkm.databinding.ItemTokoPremiumBinding
import java.text.NumberFormat
import java.util.Locale

class PembeliRiwayatAdapter : RecyclerView.Adapter<PembeliRiwayatAdapter.HomeViewHolder>() {
	private var riwayatList: List<RiwayatModel> = emptyList()

	var itemClickListener: ((item: RiwayatModel) -> Unit)? = null

	private lateinit var onItemClickCallBack: OnItemClickCallBack

	fun setOnItemClickCallback(onItemClickCallBack: OnItemClickCallBack) {
		this.onItemClickCallBack = onItemClickCallBack
	}

	private val diffCallback = object : DiffUtil.ItemCallback<RiwayatModel>() {
		override fun areItemsTheSame(oldItem: RiwayatModel, newItem: RiwayatModel): Boolean {
			return oldItem.idProduct == newItem.idProduct
		}

		@SuppressLint("DiffUtilEquals")
		override fun areContentsTheSame(oldItem: RiwayatModel, newItem: RiwayatModel): Boolean {
			return oldItem.hashCode() == newItem.hashCode()
		}
	}

	private val differ = AsyncListDiffer(this, diffCallback)

	fun setList(riwayatList: List<RiwayatModel>?) {
		differ.submitList(riwayatList)
	}

	inner class HomeViewHolder(private val binding: ItemRiwayatBinding) :
		RecyclerView.ViewHolder(binding.root) {
		@SuppressLint("SetTextI18n")
		fun bind(item: RiwayatModel) {
			binding.apply {
				tvTanggal.text = item.tanggalBelanja
				if (item.statusBelanja == "Berhasil") {
					tvStatus.text = item.statusBelanja
					tvStatus.setTextColor(Color.parseColor("#42AB50")) // Gantilah dengan kode warna RGB yang sesuai
				} else {
					tvStatus.text = item.statusBelanja
					tvStatus.setTextColor(Color.parseColor("#C9C9C9")) // Gantilah dengan kode warna RGB yang sesuai
				}


				itemName.text = item.namaProduct
				itemQuantity.text = item.jumlahBarang
				// Menampilkan harga dalam format rupiah
				val formattedPrice = formatToRupiah(item.totalBelanja.toString())
				tvHarga.text = formattedPrice

				Glide.with(ivTokoPremium)
					.load(item.gambarProduct)
					.into(ivTokoPremium)

			}

			binding.root.setOnClickListener {
				itemClickListener?.invoke(item)

			}
		}
	}
	private fun formatToRupiah(price: String): String {
		val localeID = Locale("id", "ID")
		val numberFormat = NumberFormat.getCurrencyInstance(localeID)
		val priceValue = price.toDoubleOrNull() ?: 0.0
		return numberFormat.format(priceValue)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
		val binding =
			ItemRiwayatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return HomeViewHolder(binding)
	}

	override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
		holder.bind(differ.currentList[position])
	}

	override fun getItemCount(): Int {
		return differ.currentList.size
	}

	interface OnItemClickCallBack {
		fun onItemClicked(data: RiwayatModel)
	}
}
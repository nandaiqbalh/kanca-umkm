package com.nandaiqbalh.kancaumkm.presentation.ui.home.pembeli.premium.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nandaiqbalh.kancaumkm.data.local.pembeli.tokopremium.model.TokoPremium
import com.nandaiqbalh.kancaumkm.databinding.ItemTokoPremiumBinding
import java.text.NumberFormat
import java.util.Locale

class PembeliTokoPremiumAdapter : RecyclerView.Adapter<PembeliTokoPremiumAdapter.HomeViewHolder>() {
	private var tokoList: List<TokoPremium> = emptyList()

	var itemClickListener: ((item: TokoPremium) -> Unit)? = null

	private lateinit var onItemClickCallBack: OnItemClickCallBack

	fun setOnItemClickCallback(onItemClickCallBack: OnItemClickCallBack) {
		this.onItemClickCallBack = onItemClickCallBack
	}

	private val diffCallback = object : DiffUtil.ItemCallback<TokoPremium>() {
		override fun areItemsTheSame(oldItem: TokoPremium, newItem: TokoPremium): Boolean {
			return oldItem.idProduct == newItem.idProduct
		}

		@SuppressLint("DiffUtilEquals")
		override fun areContentsTheSame(oldItem: TokoPremium, newItem: TokoPremium): Boolean {
			return oldItem.hashCode() == newItem.hashCode()
		}
	}

	private val differ = AsyncListDiffer(this, diffCallback)

	fun setList(cities: List<TokoPremium>?) {
		differ.submitList(cities)
	}

	inner class HomeViewHolder(private val binding: ItemTokoPremiumBinding) :
		RecyclerView.ViewHolder(binding.root) {
		@SuppressLint("SetTextI18n")
		fun bind(item: TokoPremium) {
			binding.apply {
				binding.itemName.text = item.namaProduct
				binding.itemLocation.text = item.lokasiTokoPremium
				// Menampilkan harga dalam format rupiah
				val formattedPrice = formatToRupiah(item.hargaProduct.toString())
				itemPrice.text = formattedPrice

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
			ItemTokoPremiumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return HomeViewHolder(binding)
	}

	override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
		holder.bind(differ.currentList[position])
	}

	override fun getItemCount(): Int {
		return differ.currentList.size
	}

	interface OnItemClickCallBack {
		fun onItemClicked(data: TokoPremium)
	}
}
package com.nandaiqbalh.kancaumkm.presentation.ui.home.pembeli.beranda

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nandaiqbalh.kancaumkm.R
import com.nandaiqbalh.kancaumkm.data.local.product.models.Product

class ProductAdapter(private var data: List<Product>) :
	RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

	private lateinit var contextAdapter: Context

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		contextAdapter = parent.context
		val inflatedView = layoutInflater.inflate(R.layout.item_produk, parent, false)
		return ViewHolder(inflatedView)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bindItem(data[position], contextAdapter)
	}

	override fun getItemCount(): Int {
		return data.size
	}

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		private var ivGambarProduct: ImageView = view.findViewById(R.id.iv_gambar_item)
		private var constrainItemCatering: ConstraintLayout =
			view.findViewById(R.id.constraint_item_catering)

		fun bindItem(data: Product, context: Context) {

//			constrainItemCatering.setOnClickListener {
//				val intent = Intent(context, DetailProdukActivity::class.java)
//				intent.putExtra("data", data.idCatering)
//				context.startActivity(intent)
//			}

			Glide.with(context)
				.load(data.gambarProduct)
				.into(ivGambarProduct)
		}
	}
}
package com.nandaiqbalh.kancaumkm.data.local.pembeli.tokopremium.model

import android.os.Parcelable
import com.nandaiqbalh.kancaumkm.data.local.product.models.Product
import kotlinx.parcelize.Parcelize

@Parcelize
class TokoPremium(
	var idProduct: Int?,
	var gambarProduct: Int,
	var namaProduct: String?,
	var hargaProduct: String?,
	var lokasiTokoPremium: String,
) : Parcelable
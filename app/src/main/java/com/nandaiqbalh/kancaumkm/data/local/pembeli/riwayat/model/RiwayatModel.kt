package com.nandaiqbalh.kancaumkm.data.local.pembeli.riwayat.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class RiwayatModel(
	var idProduct: Int?,
	var gambarProduct: Int,
	var namaProduct: String?,
	var hargaProduct: String?,
	var tanggalBelanja: String?,
	var statusBelanja: String?,
	var jumlahBarang: String?,
	var totalBelanja: String?
) : Parcelable
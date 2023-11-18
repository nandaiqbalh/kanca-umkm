package com.nandaiqbalh.kancaumkm.presentation.ui.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.nandaiqbalh.kancaumkm.data.local.datastore.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
	private val dataStoreManager: DataStoreManager
) : ViewModel() {

	fun saveCredential(email: String, password: String) = CoroutineScope(Dispatchers.IO).launch {
		dataStoreManager.saveCredentials(email, password)
	}
}
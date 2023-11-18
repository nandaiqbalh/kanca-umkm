package com.nandaiqbalh.kancaumkm.presentation.ui.auth.login

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
class LoginViewModel @Inject constructor(
	private val dataStoreManager: DataStoreManager
) : ViewModel() {

	fun getStatusLogin(): LiveData<Boolean> = dataStoreManager.getStatusLogin.asLiveData()

	fun setStatusLogin(status: Boolean) = CoroutineScope(Dispatchers.IO).launch {
		dataStoreManager.setStatusLogin(status)
	}

	fun getSavedCredential(): LiveData<Pair<String?, String?>> =
		dataStoreManager.getSavedCredentials().asLiveData()

}
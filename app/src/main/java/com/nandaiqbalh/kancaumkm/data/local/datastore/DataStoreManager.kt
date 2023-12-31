package com.nandaiqbalh.kancaumkm.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class DataStoreManager(@ApplicationContext private val context: Context) {

	val getStatusOnboarding: Flow<Boolean> = context.dataStore.data.map {
		it[STATUS_ONBOARDING_KEY] ?: false
	}

	suspend fun setStatusOnboarding(status: Boolean) {
		context.dataStore.edit {
			it[STATUS_ONBOARDING_KEY] = status
		}
	}

	val getStatusLogin: Flow<Boolean> = context.dataStore.data.map {
		it[STATUS_LOGIN_KEY] ?: false
	}
	suspend fun setStatusLogin(status: Boolean) {
		context.dataStore.edit {
			it[STATUS_LOGIN_KEY] = status
		}
	}
	suspend fun saveCredentials(email: String, password: String) {
		context.dataStore.edit {
			it[EMAIL_KEY] = email
			it[PASSWORD_KEY] = password
		}
	}

	fun getSavedCredentials(): Flow<Pair<String?, String?>> {
		val usernameFlow = context.dataStore.data.map { it[EMAIL_KEY] }
		val passwordFlow = context.dataStore.data.map { it[PASSWORD_KEY] }

		return usernameFlow.combine(passwordFlow) { username, password ->
			Pair(username, password)
		}
	}

	suspend fun clear() {
		context.dataStore.edit {
			it.clear()
		}
	}

	companion object {
		private const val DATASTORE_NAME = "datastore_preferences"
		private val STATUS_ONBOARDING_KEY = booleanPreferencesKey("status_onboarding_key")
		private val STATUS_LOGIN_KEY = booleanPreferencesKey("status_login_key")
		private val EMAIL_KEY = stringPreferencesKey("email_key")
		private val PASSWORD_KEY = stringPreferencesKey("password_key")
		private val Context.dataStore by preferencesDataStore(
			name = DATASTORE_NAME
		)
	}
}

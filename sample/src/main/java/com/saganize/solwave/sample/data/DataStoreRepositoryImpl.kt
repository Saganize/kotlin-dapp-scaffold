package com.saganize.solwave.sample.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.saganize.solwave.sample.domain.repository.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext

class DataStoreRepositoryImpl(private val context: Context) : DataStoreRepository {
    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "datastore")
        val KEY_STRING = stringPreferencesKey("wallet_key")
    }

    override suspend fun savePublicKey(value: String) {
        withContext(Dispatchers.IO){
            context.dataStore.edit { preferences ->
                preferences[KEY_STRING] = value
            }
        }
    }

    override suspend fun getPublicKey(): String? {
        return withContext(Dispatchers.IO){
            val preferences = context.dataStore.data.firstOrNull()
            preferences?.get(KEY_STRING)
        }
    }
}

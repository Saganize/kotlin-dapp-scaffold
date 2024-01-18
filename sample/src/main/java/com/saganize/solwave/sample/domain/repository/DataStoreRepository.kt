package com.saganize.solwave.sample.domain.repository

interface DataStoreRepository {
    suspend fun savePublicKey(value: String)
    suspend fun getPublicKey(): String?
}
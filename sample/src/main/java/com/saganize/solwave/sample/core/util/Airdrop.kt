package com.saganize.solwave.sample.core.util

import android.util.Log
import com.saganize.solwave.sample.core.di.TAG
import com.solana.Solana
import com.solana.api.requestAirdrop
import com.solana.core.PublicKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun Solana.airdrop(publicKey: String): Result<String>? = withContext(Dispatchers.IO) {
    try {
        this@airdrop.api.requestAirdrop(
            address = PublicKey(publicKey),
            lamports = 1_000_000_000L
        )
    } catch (e: Exception) {
        Log.e(TAG, "Airdrop Error", e)
        null
    }
}
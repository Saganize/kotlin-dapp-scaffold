package com.saganize.solwave.sample.core.util

import android.util.Log
import com.saganize.solwave.sample.core.di.TAG
import com.solana.Solana
import com.solana.api.Api
import com.solana.api.GetBalanceRequest
import com.solana.api.getBalance
import com.solana.core.PublicKey
import com.solana.networking.Commitment
import com.solana.networking.HttpNetworkingRouter
import com.solana.networking.RPCEndpoint
import com.solana.networking.makeRequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


suspend fun getBalance(walletKey: String): Double? {

    val solana = Solana(HttpNetworkingRouter(RPCEndpoint.devnetSolana))

    return withContext(Dispatchers.IO) {
        try {
            val result = solana.api.getBalance(
                PublicKey(walletKey),
            )
            result.getOrNull()?.toDouble()
        } catch (e: Exception) {
            Log.e(TAG, "Balance error:", e)
            null
        }
    }
}

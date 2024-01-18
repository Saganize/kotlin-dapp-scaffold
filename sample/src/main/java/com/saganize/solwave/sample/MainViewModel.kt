package com.saganize.solwave.sample

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saganize.solwave.sample.core.util.airdrop
import com.saganize.solwave.sample.core.util.getBalance
import com.saganize.solwave.sample.core.util.showToast
import com.saganize.solwave.sample.core.util.toBalance
import com.saganize.solwave.sample.data.DataStoreRepositoryImpl
import com.solana.Solana
import com.solana.networking.HttpNetworkingRouter
import com.solana.networking.RPCEndpoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


data class MainState(
    val publicKey: String? = null,
    val balance: String = "",
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStoreRepositoryImpl: DataStoreRepositoryImpl
) : ViewModel() {
    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    private var solana: Solana = Solana(HttpNetworkingRouter(RPCEndpoint.devnetSolana))

    init {
        viewModelScope.launch {
            dataStoreRepositoryImpl.getPublicKey()?.let {
                _state.value = state.value.copy(publicKey = it)
                checkBalance(it)
            }
        }
    }

    fun savePublicKey(walletKey: String) {
        viewModelScope.launch {
            dataStoreRepositoryImpl.savePublicKey(walletKey)
            _state.value = state.value.copy(publicKey = walletKey)
            checkBalance(walletKey)
        }
    }

    fun airDrop(context: Context) {
        state.value.publicKey?.let { publicKey ->
            viewModelScope.launch {
                solana.airdrop(publicKey)?.let { result ->
                    result.onSuccess {
                        context.showToast(it)
                    }.onFailure {
                        val err = it.message.toString()
                        context.showToast(err)
                    }
                } ?: run{
                    context.showToast("Try again later.")
                }
            }
            checkBalance(publicKey)
        } ?: run {
            context.showToast("Please connect your wallet first.")
        }

    }

    private fun checkBalance(walletKey: String) {
        viewModelScope.launch {
            getBalance(walletKey)?.let {
                _state.value = state.value.copy(balance = it.toBalance())
            }
        }
    }
}



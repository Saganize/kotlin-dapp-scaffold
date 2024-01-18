package com.saganize.solwave.sample

import android.icu.util.Calendar
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.saganize.solwave.Solwave
import com.saganize.solwave.sample.ui.components.MainTextButton
import com.saganize.solwave.sample.ui.components.TopBar
import com.saganize.solwave.sample.ui.theme.White
import com.saganize.solwave.sample.ui.theme.interFamily
import com.saganize.solwave.sample.core.di.TAG
import com.saganize.solwave.sample.core.util.cropKey
import com.saganize.solwave.sample.core.util.showToast
import com.solana.core.PublicKey
import com.solana.core.Transaction
import com.solana.programs.SystemProgram
import com.solana.vendor.TweetNaclFast
import org.bitcoinj.core.Base58


@Composable
fun App(
    viewModel: MainViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val context = LocalContext.current
    val solwave = Solwave(context, apiKey = "YOUR_API_KEY")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.mask),
                contentScale = ContentScale.FillBounds
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TopBar()

            Spacer(modifier = Modifier.size(24.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MainTextButton(
                    text = state.publicKey?.cropKey() ?: "Select Wallet",
                    background = false,
                ) {
                    solwave.selectWallet(
                        onSuccess = { walletKey ->
                            viewModel.savePublicKey(walletKey)
                        },
                        onFailure = { error ->
                            Log.d(TAG, error.message)
                        }
                    )
                }

                Text(
                    text = state.balance,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = interFamily,
                        fontWeight = FontWeight(400),
                        color = White,
                        letterSpacing = 0.29.sp,
                    )
                )
            }

            Spacer(modifier = Modifier.size(60.dp))

            Text(
                text = "Solana Mobile Scaffold",
                style = TextStyle(
                    fontSize = 28.sp,
                    lineHeight = 30.sp,
                    fontFamily = interFamily,
                    fontWeight = FontWeight(600),
                    color = White,
                    textAlign = TextAlign.Center,
                )
            )
            Text(
                text = "Unleash the full power of Blockchain\n with Solana and Kotlin.",
                modifier = Modifier.alpha(0.7f),
                style = TextStyle(
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    fontFamily = interFamily,
                    fontWeight = FontWeight(300),
                    color = White,
                    textAlign = TextAlign.Center,
                )
            )
        }

        Column {
            MainTextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                text = "AIRDROP 1 Sol",
            ) {
                viewModel.airDrop(context)
            }

            Spacer(modifier = Modifier.size(24.dp))

            MainTextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                text = "SEND TRANSACTION",
            ) {
                state.publicKey?.let {

                    val solTransferInstruction = SystemProgram.transfer(
                        PublicKey(state.publicKey),
                        PublicKey("Bu3mTU2X7SoZUkyNU37jispVqRLkSSwiQuN6rGbvQx9f"),
                        1000L
                    )
                    val transaction = Transaction().addInstruction(solTransferInstruction)

                    solwave.performTransaction(transaction,
                        onSuccess = {
                            context.showToast(it)
                        },
                        onFailure = {
                            context.showToast(it.message)
                        }
                    )
                } ?: run {
                    context.showToast("Please connect your wallet first.")
                }
            }

            Spacer(modifier = Modifier.size(24.dp))

            MainTextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                text = "SIGN MESSAGE",
            ) {
                state.publicKey?.let {
                    val message = "lazy fox jumps over the brown dog"
                    solwave.signMessage(
                        message = message,
                        onSuccess = { messageSignature ->
                            Log.d(TAG, messageSignature)

                            val publicKeyByteArray = PublicKey(it).toByteArray()
                            val messageBytes = message.toByteArray()

                            val signatureByteArray = Base58.decode(messageSignature)

                            // Verify the signature
                            val isVerified = verifySignature(
                                message = messageBytes,
                                publicKey = publicKeyByteArray,
                                signature = signatureByteArray,
                            )

                            val toastMessage = if (isVerified) "Message is verified" else "Message is not verified"
                            Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show()

                            Log.d(TAG, "signature verified: $isVerified")
                        },
                        onFailure = { error ->
                            Log.d(TAG, error.message)
                        },
                    )
                } ?: run {
                    context.showToast("Please connect your wallet first.")
                }
            }

            Spacer(modifier = Modifier.size(100.dp))
        }

        // get the year dynamically
        Text(
            text = "© ${Calendar.getInstance().get(Calendar.YEAR)} Saganize",
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 14.sp,
                fontFamily = interFamily,
                fontWeight = FontWeight(400),
                color = Color(0xFFF9F9F9),
                textAlign = TextAlign.Center,
            )
        )
    }
}

private fun verifySignature(message: ByteArray, publicKey: ByteArray, signature: ByteArray): Boolean {
    return when {
        signature.size != 64 -> false
        publicKey.size != 32 -> false
        else -> {
            val sm = signature + message
            val m = ByteArray(sm.size)
            TweetNaclFast.crypto_sign_open(m, -1L, sm, 0, sm.size, publicKey) >= 0
        }
    }
}


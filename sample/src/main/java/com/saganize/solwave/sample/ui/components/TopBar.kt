package com.saganize.solwave.sample.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saganize.solwave.sample.R
import com.saganize.solwave.sample.ui.theme.GrayDisabled
import com.saganize.solwave.sample.ui.theme.White
import com.saganize.solwave.sample.ui.theme.bold
import com.saganize.solwave.sample.ui.theme.interFamily
import com.saganize.solwave.sample.ui.theme.light
import com.saganize.solwave.sample.ui.theme.mediumEmphasis

@Composable
fun TopBar() {

    val context = LocalContext.current

    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Solwave",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = interFamily,
                    fontWeight = FontWeight(400),
                    color = White,
                    letterSpacing = 0.29.sp
                )
            )
            Spacer(modifier = Modifier.padding(1.dp))
            Text(
                text = buildAnnotatedString {
                    append("Powered by ")
                    withStyle(
                        style = MaterialTheme.typography.caption.bold.toSpanStyle()
                    ) {
                        append("Saga")
                    }
                    withStyle(
                        style = MaterialTheme.typography.caption.toSpanStyle()
                    ) {
                        append("nize")
                    }
                },
                style = MaterialTheme.typography.caption.light,
                color = GrayDisabled.mediumEmphasis
            )
        }

        Row {
            IconButton(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/saganize"))
                    context.startActivity(intent)
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.twitter),
                    contentDescription = null
                )
            }
            IconButton(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Saganize/kotlin-dapp-scaffold"))
                    context.startActivity(intent)
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.github),
                    contentDescription = null
                )
            }
            IconButton(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.saganize.com/"))
                    context.startActivity(intent)
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.saga),
                    contentDescription = null
                )
            }
        }
    }
}

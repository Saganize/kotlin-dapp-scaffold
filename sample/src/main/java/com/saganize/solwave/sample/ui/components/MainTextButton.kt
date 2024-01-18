package com.saganize.solwave.sample.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saganize.solwave.sample.ui.theme.SaganizeBlue
import com.saganize.solwave.sample.ui.theme.White
import com.saganize.solwave.sample.ui.theme.interFamily

@Composable
fun MainTextButton(
    modifier: Modifier = Modifier,
    text: String,
    background: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .shadow(
                elevation = 7.dp,
                spotColor = Color(0x14000000),
                ambientColor = Color(0x14000000)
            ),
        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 32.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (background) SaganizeBlue else Color.Transparent,
            contentColor = White
        ),
        border = BorderStroke(
            width = 1.dp,
            brush = Brush.verticalGradient(
                colors = listOf(Color(0x80FFFFFF), Color(0x1AFFFFFF)),
                startY = 0f,
                endY = 50f
            )
        ),
        shape = RoundedCornerShape(15.dp),
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = interFamily,
                color = White,
                textAlign = TextAlign.Center,
            ),
        )
    }
}
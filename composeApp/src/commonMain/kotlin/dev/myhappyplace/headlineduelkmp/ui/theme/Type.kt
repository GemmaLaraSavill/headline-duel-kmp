package dev.myhappyplace.headlineduelkmp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

expect val fontScale: Float

val Int.ssp get() = (this * fontScale).sp
val Double.ssp get() = (this * fontScale).sp

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.ssp,
        lineHeight = 24.ssp,
        letterSpacing = 0.5.ssp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.ssp,
        lineHeight = 28.ssp,
        letterSpacing = 0.ssp
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 24.ssp,
        lineHeight = 32.ssp,
        letterSpacing = 0.ssp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 28.ssp,
        lineHeight = 36.ssp,
        letterSpacing = 0.ssp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.ssp,
        lineHeight = 20.ssp,
        letterSpacing = 0.25.ssp
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 30.ssp,
        lineHeight = 36.ssp,
        letterSpacing = 0.ssp
    )
)
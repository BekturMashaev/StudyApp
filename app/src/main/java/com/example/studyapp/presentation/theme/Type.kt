package com.example.studyapp.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.studyapp.R

val Poppins = FontFamily(
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_regular),
    Font(R.font.poppins_semibold, FontWeight.SemiBold)
)

//** typography **\\

val AppTypography: Typography = Typography(
    headlineMedium = TextStyle(
        fontFamily = Poppins,
        fontSize = sp17,
        lineHeight = sp24,
        fontWeight = FontWeight.Medium,
    ),
    displayMedium = TextStyle(
        fontFamily = Poppins,
        fontSize = sp17,
        lineHeight = sp24,
        fontWeight = FontWeight.SemiBold,
    ),
    titleMedium = TextStyle(
        fontFamily = Poppins,
        fontSize = sp15,
        lineHeight = sp25,
        fontWeight = FontWeight.Normal
    ),
    titleSmall = TextStyle(
        fontFamily = Poppins,
        fontSize = sp13,
        lineHeight = sp22,
        fontWeight = FontWeight.Normal
    ),
    displaySmall= TextStyle(
        fontFamily = Poppins,
        fontSize = sp13,
        lineHeight = sp24,
        fontWeight = FontWeight.Medium
    ),
)
//** shape **\\

val shape = Shapes(
    medium = RoundedCornerShape(dp10),
    large = RoundedCornerShape(dp20),
    extraLarge = RoundedCornerShape(dp25),
)

//** colors **\\

val DarkColorScheme = darkColorScheme(
    onBackground = White,
    background = DarkBackground,
    primary = Blue,
    surface = DarkGray,
    onSurface = BorderDark,
    surfaceTint = TextFieldDarkGray,
    errorContainer = DarkREd.copy(alpha = 0.4f),
    onError = LightRed,
)

val LightColorScheme = lightColorScheme(
    onBackground = Black,
    background = White,
    primary = WhiteLightBLue,
    surface = WhiteGray,
    onSurface = BorderGray,
    surfaceTint = TextFieldGray,
    errorContainer = LightRed.copy(alpha = 0.4f),
    onError = LightRed,
)
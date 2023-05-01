package com.hsa.pakcables.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorPalette = lightColors(
    primary = Color(0xFF009688),
    secondary = Color(0xFFF5F5F5),
    onSecondary = Color(0xFF212121),
    surface = Color.White,
    onSurface = Color(0xFF212121),
    primaryVariant = Color(0xFF00796B),
    secondaryVariant = Color(0xFFBDBDBD),
    background = Color.White,
    onBackground = Color(0xFF212121),
    error = Color(0xFFB00020),
    onError = Color.White
)

private val DarkColorPalette = darkColors(
    primary = Color(0xFF009688),
    secondary = Color(0xFF212121),
    onSecondary = Color.White,
    surface = Color(0xFF121212),
    onSurface = Color.White,
    primaryVariant = Color(0xFF00796B),
    secondaryVariant = Color(0xFFBDBDBD),
    background = Color(0xFF121212),
    onBackground = Color.White,
    error = Color(0xFFCF6679),
    onError = Color.Black,
)

@Composable
fun PakCablesTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val systemUiController = rememberSystemUiController()

        systemUiController.setStatusBarColor(
            color = GradientPurple
        )
        systemUiController.setNavigationBarColor(
            color = GradientPurple
        )

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
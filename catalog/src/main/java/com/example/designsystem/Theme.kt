package com.example.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color


// 1) Colors (replace with your palette)

    val LightColors = lightColorScheme(
        primary = Color(0xFF6750A4),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFEADDFF),
    onPrimaryContainer = Color(0xFF21005D),
)
    val DarkColors = darkColorScheme(
    primary = Color(0xFFD0BCFF),
    onPrimary = Color(0xFF381E72),
    primaryContainer = Color(0xFF4F378B),
    onPrimaryContainer = Color(0xFFEADDFF),
    // ... fill out the rest as needed
)

// 2) Typography (Material3)
val AppTypography = Typography(
    // Define only the styles you need; others will fall back to defaults
    // Example:
    // displayLarge = TextStyle(
    //     fontFamily = FontFamily.Default,
    //     fontWeight = FontWeight.Normal,
    //     fontSize = 57.sp,
    //     lineHeight = 64.sp,
    //     letterSpacing = 0.sp
    // )
)

// 3) Shapes (Material3 has 5 slots)
val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

@Composable
fun AppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        //typography = AppTypography,
        //shapes = AppShapes,
        content = content
    )
}

// Helper to convert ARGB ints to Color without importing Material2 Color
private fun Int.toColor(): androidx.compose.ui.graphics.Color =
    androidx.compose.ui.graphics.Color(this)
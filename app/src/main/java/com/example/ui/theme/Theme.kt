package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
  darkColorScheme(
    primary = MinimalPrimaryBlue,
    primaryContainer = MinimalDarkSlateVariant,
    onPrimaryContainer = Color.White,
    secondary = MinimalEmeraldGreen,
    secondaryContainer = MinimalDarkSlateVariant,
    tertiary = MinimalAmber,
    background = MinimalDarkSlate,
    surface = MinimalDarkSlateVariant,
    surfaceVariant = MinimalDarkSlate,
    outline = Color(0xFF334155),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color(0xFFF8FAFC),
    onSurface = Color(0xFFF8FAFC),
    onSurfaceVariant = Color(0xFF94A3B8)
  )

private val LightColorScheme =
  lightColorScheme(
    primary = MinimalPrimaryBlue,
    primaryContainer = MinimalPrimaryContainer,
    onPrimaryContainer = MinimalOnPrimaryContainer,
    secondary = MinimalDarkSlate,
    secondaryContainer = MinimalSurfaceVariant,
    tertiary = MinimalEmeraldGreen,
    tertiaryContainer = MinimalGreenContainer,
    onTertiaryContainer = MinimalOnGreenContainer,
    background = MinimalBg,
    surface = MinimalSurface,
    surfaceVariant = MinimalSurfaceVariant,
    outline = MinimalOutline,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = MinimalTextPrimary,
    onSurface = MinimalTextPrimary,
    onSurfaceVariant = MinimalTextSecondary
  )

@Composable
fun SdeRoadmapTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false, // Set false to ensure branded color theme
  content: @Composable () -> Unit,
) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}

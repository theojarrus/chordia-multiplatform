package com.neotive.chordia.sample.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
private fun getColorScheme(darkTheme: Boolean = isSystemInDarkTheme()): ColorScheme {
    return when {
        isDynamicColorSupported && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
        isDynamicColorSupported && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
        else -> darkScheme
    }
}

@Composable
fun ChordiaTheme(isDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = getColorScheme(isDarkTheme),
        typography = typography,
        shapes = shapes,
        content = content
    )
}

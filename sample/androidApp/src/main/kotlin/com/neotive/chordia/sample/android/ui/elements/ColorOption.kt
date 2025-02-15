package com.neotive.chordia.sample.android.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ColorOption(
    title: String,
    colors: List<Color>,
    modifier: Modifier = Modifier,
    space: Dp = 12.dp,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    onClick: (() -> Unit)? = null
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = title, style = textStyle)
        Spacer(modifier = Modifier.height(space))
        Card(modifier = Modifier, onClick = onClick) {
            Row(modifier = Modifier.fillMaxSize()) {
                colors.forEach { color ->
                    Box(modifier = Modifier.fillMaxHeight().weight(1f).background(color))
                }
            }
        }
    }
}

@Composable
private fun Card(modifier: Modifier, onClick: (() -> Unit)?, content: @Composable ColumnScope.() -> Unit) {
    if (onClick != null) {
        Card(modifier = modifier, onClick = onClick, content = content)
    } else {
        Card(modifier = modifier, content = content)
    }
}

private fun Modifier.ratio(aspectRatio: Number?): Modifier {
    return aspectRatio?.toFloat()?.let(::aspectRatio) ?: this
}

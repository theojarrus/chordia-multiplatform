package com.neotive.chordia.sample.android.ui.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.neotive.chordia.sample.android.ui.ext.clickable




@Composable
fun TextOption(
    title: String,
    content: String,
    modifier: Modifier = Modifier,
    space: Dp = 12.dp,
    alignment: Alignment = Alignment.Center,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    onClick: (() -> Unit)? = null
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = title, style = textStyle)
        Spacer(modifier = Modifier.height(space))
        Card(onClick = onClick) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = content, style = textStyle, modifier = Modifier.align(alignment))
            }
        }
    }
}

@Composable
private fun Card(onClick: (() -> Unit)?, content: @Composable ColumnScope.() -> Unit) {
    if (onClick != null) {
        Card(onClick = onClick, content = content)
    } else {
        Card(content = content)
    }
}

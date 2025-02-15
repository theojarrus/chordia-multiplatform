package com.neotive.chordia.sample.android.ui.elements

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun Button(
    text: String,
    modifier: Modifier = Modifier,
    progress: Boolean = false,
    shape: Shape = MaterialTheme.shapes.medium,
    onClick: () -> Unit
) {
    Button(
        onClick = { if (!progress) onClick() },
        shape = shape,
        modifier = modifier.height(55.dp)
    ) {
        Text(text)
    }
}

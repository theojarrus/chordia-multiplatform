package com.neotive.chordia.sample.android.ui.ext

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset

fun Modifier.clickable(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: (() -> Unit)? = null
) = run  {
    onClick?.let {
        clickable(
            enabled = enabled,
            onClickLabel = onClickLabel,
            role = role,
            onClick
        )
    } ?: this
}

fun Modifier.stretch(horizontal: Dp = 0.dp, vertical: Dp = 0.dp) = this then stretch(
    start = horizontal,
    top = vertical,
    end = horizontal,
    bottom = vertical
)

fun Modifier.stretch(
    start: Dp = 0.dp,
    top: Dp = 0.dp,
    end: Dp = 0.dp,
    bottom: Dp = 0.dp
) = this then layout { measurable, constraints ->
    val offsetHorizontal = (start + end).roundToPx()
    val offsetVertical = (top + bottom).roundToPx()
    val offsetConstraints = constraints.offset(horizontal = offsetHorizontal, vertical = offsetVertical)
    val placeable = measurable.measure(offsetConstraints)
    layout(placeable.width, placeable.height) { placeable.placeRelative(0, 0) }
}

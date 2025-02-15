package com.neotive.chordia.sample.android.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.neotive.chordia.sample.android.ui.ext.clickable

@Composable
fun Cell(
    title: String,
    modifier: Modifier = Modifier,
    icon: ImageVector? = CellDefaults.cellIcon,
    textStyle: TextStyle = CellDefaults.cellTextStyle,
    paddingValues: PaddingValues = CellDefaults.defaultCellPadding(),
    onClick: (() -> Unit)? = null,
    value: @Composable () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(paddingValues),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = textStyle.copy(color = MaterialTheme.colorScheme.onSurface)
        )
        Spacer(modifier = Modifier.weight(1f))
        value()
        icon?.let {
            Spacer(modifier = Modifier.size(8.dp))
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF9299A2)
            )
        }
    }
}

@Composable
fun TextCell(
    title: String,
    modifier: Modifier = Modifier,
    value: String? = null,
    icon: ImageVector? = CellDefaults.cellIcon,
    textStyle: TextStyle = CellDefaults.cellTextStyle,
    paddingValues: PaddingValues = CellDefaults.defaultCellPadding(),
    onClick: (() -> Unit)? = null
) = Cell(
    title = title,
    modifier = modifier,
    icon = icon,
    textStyle = textStyle,
    paddingValues = paddingValues,
    onClick = onClick
) {
    value?.let {
        Text(
            text = value,
            style = textStyle.copy(color = Color(0xFF9299A2))
        )
    }
}

@Composable
fun ColorCell(
    title: String,
    modifier: Modifier = Modifier,
    values: List<Color> = emptyList(),
    icon: ImageVector? = CellDefaults.cellIcon,
    textStyle: TextStyle = CellDefaults.cellTextStyle,
    paddingValues: PaddingValues = CellDefaults.defaultCellPadding(),
    onClick: (() -> Unit)? = null
) = Cell(
    title = title,
    modifier = modifier,
    icon = icon,
    textStyle = textStyle,
    paddingValues = paddingValues,
    onClick = onClick
) {
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        values.forEach {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(it)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = CircleShape
                    )
            )
        }
    }
}

object CellDefaults {

    private val defaultPadding: Dp get() = 12.dp
    private val startPadding: Dp get() = 20.dp
    private val extraPadding: Dp get() = 6.dp

    val cellIcon: ImageVector get() = Icons.AutoMirrored.Rounded.KeyboardArrowRight
    val cellTextStyle: TextStyle @Composable get() = MaterialTheme.typography.bodyLarge

    fun defaultCellPadding(
        start: Dp = startPadding,
        top: Dp = defaultPadding,
        end: Dp = defaultPadding,
        bottom: Dp = defaultPadding
    ): PaddingValues = PaddingValues(
        start = start,
        top = top,
        end = end,
        bottom = bottom
    )

    fun firstCellPadding(
        start: Dp = startPadding,
        top: Dp = defaultPadding + extraPadding,
        end: Dp = defaultPadding,
        bottom: Dp = defaultPadding
    ): PaddingValues = PaddingValues(
        start = start,
        top = top,
        end = end,
        bottom = bottom
    )

    fun lastCellPadding(
        start: Dp = startPadding,
        top: Dp = defaultPadding,
        end: Dp = defaultPadding,
        bottom: Dp = defaultPadding + extraPadding
    ): PaddingValues = PaddingValues(
        start = start,
        top = top,
        end = end,
        bottom = bottom
    )
}

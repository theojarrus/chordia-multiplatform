package com.neotive.chordia.sample.android.ui.elements

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropdownTextField(
    modifier: Modifier = Modifier,
    initial: String = "",
    items: Map<String, T>,
    shape: Shape = MaterialTheme.shapes.medium,
    colors: TextFieldColors = TextFieldDefaults.colors(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent
    ),
    onItemClick: (T) -> Unit
) {
    var value by remember { mutableStateOf(initial) }
    var expanded by remember { mutableStateOf(false) }
    val enabled = items.isNotEmpty()
    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = enabled && !expanded }) {
        TextField(
            value = value,
            onValueChange = {},
            enabled = enabled,
            readOnly = true,
            singleLine = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            shape = shape,
            colors = colors,
            modifier = modifier.menuAnchor()
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = !expanded }) {
            items.forEach { (name, item) ->
                DropdownMenuItem(
                    text = { Text(text = name) },
                    onClick = {
                        expanded = !expanded
                        value = name
                        onItemClick(item)
                    }
                )
            }
        }
    }
}

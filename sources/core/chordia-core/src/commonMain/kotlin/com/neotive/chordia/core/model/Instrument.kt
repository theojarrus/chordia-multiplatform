package com.neotive.chordia.core.model

import com.neotive.chordia.core.model.Type.Keys
import com.neotive.chordia.core.model.Type.Strings
import com.neotive.chordia.utils.scale

data class Instrument(
    val type: Type,
    val length: Length,
    val drop: Drop,
    val ordered: Boolean
) {

    val lines: List<Note> get() = when (type) {
        Keys -> drop.lines.toList().scale(length.value)
        Strings -> drop.lines.toList()
    }
}

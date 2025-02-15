package com.neotive.chordia.rendering.api.model.shape

import com.neotive.chordia.rendering.api.model.shape.HexColor.LIGHT_GRAY
import com.neotive.chordia.rendering.api.model.shape.HexColor.LIGHT_SLATE_GRAY
import com.neotive.chordia.rendering.api.model.shape.HexColor.WHITE

data class Theme(
    val primaryColor: Color = LIGHT_SLATE_GRAY.color,
    val secondaryColor: Color = LIGHT_GRAY.color,
    val colorOnPrimary: Color = WHITE.color,
    val backgroundColor: Color = WHITE.color,
)

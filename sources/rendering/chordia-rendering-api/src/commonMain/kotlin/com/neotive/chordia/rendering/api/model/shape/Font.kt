package com.neotive.chordia.rendering.api.model.shape

import com.neotive.chordia.rendering.api.model.shape.FontFamily.SystemUi
import com.neotive.chordia.rendering.api.model.shape.FontWeight.Bold
import com.neotive.chordia.rendering.api.model.shape.FontStyle.NORMAL

data class Font(
    val family: FontFamily = SystemUi,
    val weight: FontWeight = Bold,
    val style: FontStyle = NORMAL
)

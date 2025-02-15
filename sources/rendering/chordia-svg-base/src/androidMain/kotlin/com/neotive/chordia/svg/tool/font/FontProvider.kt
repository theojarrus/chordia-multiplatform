package com.neotive.chordia.svg.tool.font

import android.graphics.Typeface
import com.neotive.chordia.rendering.api.model.shape.Font

interface FontProvider {

    fun provide(font: Font): Typeface
}

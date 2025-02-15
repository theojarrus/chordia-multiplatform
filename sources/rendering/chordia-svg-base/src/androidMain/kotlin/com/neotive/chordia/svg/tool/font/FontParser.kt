package com.neotive.chordia.svg.tool.font

import com.neotive.chordia.rendering.api.model.shape.Font
import com.neotive.chordia.rendering.api.model.shape.FontFamily
import com.neotive.chordia.rendering.api.model.shape.FontStyle
import com.neotive.chordia.rendering.api.model.shape.FontWeight

interface FontParser {

    fun parse(fontFamily: String?, fontWeight: Int, fontStyle: String?): Font
}

class BaseFontParser : FontParser {

    override fun parse(fontFamily: String?, fontWeight: Int, fontStyle: String?): Font {
        return Font(
            family = parseFamily(fontFamily),
            weight = parseWeight(fontWeight),
            style = parseStyle(fontStyle)
        )
    }

    fun parseFamily(fontFamily: String?): FontFamily {
        return fontFamily?.let(FontFamily::get) ?: FontFamily.SystemUi
    }

    fun parseWeight(fontWeight: Int): FontWeight {
        return FontWeight.get(fontWeight)
    }

    fun parseStyle(fontStyle: String?): FontStyle {
        return fontStyle?.let { style -> enumValues<FontStyle>().firstOrNull { it.name == style.uppercase() } }
            ?: FontStyle.NORMAL
    }
}

package com.neotive.chordia.svg.tool.font

import android.graphics.Typeface
import android.graphics.Typeface.BOLD
import android.graphics.Typeface.BOLD_ITALIC
import android.graphics.Typeface.DEFAULT
import android.graphics.Typeface.DEFAULT_BOLD
import android.graphics.Typeface.ITALIC
import android.graphics.Typeface.MONOSPACE
import android.graphics.Typeface.NORMAL
import android.graphics.Typeface.SANS_SERIF
import android.graphics.Typeface.SERIF
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.P
import com.caverock.androidsvg.SVGExternalFileResolver
import com.neotive.chordia.rendering.api.model.shape.Font
import com.neotive.chordia.rendering.api.model.shape.FontFamily.Custom
import com.neotive.chordia.rendering.api.model.shape.FontFamily.Monospace
import com.neotive.chordia.rendering.api.model.shape.FontFamily.SansSerif
import com.neotive.chordia.rendering.api.model.shape.FontFamily.Serif
import com.neotive.chordia.rendering.api.model.shape.FontStyle
import com.neotive.chordia.rendering.api.model.shape.FontWeight
import com.neotive.chordia.rendering.api.model.shape.FontWeight.Bold

class FontResolver(
    private val fontProvider: FontProvider? = null,
    private val fontParser: FontParser = BaseFontParser()
) : SVGExternalFileResolver() {

    override fun resolveFont(fontFamily: String?, fontWeight: Int, fontStyle: String?): Typeface {
        val font = fontParser.parse(fontFamily, fontWeight, fontStyle)
        return fontProvider?.provide(font) ?: resolveSystemFont(font)
    }

    private fun resolveSystemFont(font: Font): Typeface {
        val typefaceStyle = resolveStyle(font)
        return when (font.family) {
            is Custom -> resolveCustomFont(font, typefaceStyle)
            else -> resolveDefaultFont(font, typefaceStyle)
        }
    }

    private fun resolveCustomFont(font: Font, typefaceStyle: Int): Typeface {
        return Typeface.create(font.family.name, typefaceStyle)
    }

    private fun resolveDefaultFont(font: Font, typefaceStyle: Int): Typeface {
        val typeface = resolveTypeface(font)
        return when {
            SDK_INT >= P -> Typeface.create(typeface, font.weight.value, font.style.isItalic())
            else -> Typeface.create(typeface, typefaceStyle)
        }
    }

    private fun resolveStyle(font: Font): Int {
        val isBold = font.weight.isBold()
        val isItalic = font.style.isItalic()
        return when {
            isBold && isItalic -> BOLD_ITALIC
            isBold -> BOLD
            isItalic -> ITALIC
            else -> NORMAL
        }
    }

    private fun resolveTypeface(font: Font): Typeface {
        return when {
            font.family is SansSerif -> SANS_SERIF
            font.family is Serif -> SERIF
            font.family is Monospace -> MONOSPACE
            font.weight.isBold() -> DEFAULT_BOLD
            else -> DEFAULT
        }
    }

    private fun FontWeight.isBold(): Boolean {
        return value >= Bold.value
    }

    private fun FontStyle.isItalic(): Boolean {
        return this == FontStyle.ITALIC
    }
}

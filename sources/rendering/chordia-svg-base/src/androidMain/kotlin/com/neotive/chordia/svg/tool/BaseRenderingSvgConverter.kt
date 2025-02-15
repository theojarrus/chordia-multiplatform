package com.neotive.chordia.svg.tool

import android.graphics.drawable.PictureDrawable
import com.caverock.androidsvg.SVG
import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.rendering.api.model.ChordiaImage
import com.neotive.chordia.svg.api.tool.RenderingSvgConverter
import com.neotive.chordia.svg.configuration.ConverterBinding
import com.neotive.chordia.svg.tool.font.FontResolver

actual class BaseRenderingSvgConverter : RenderingSvgConverter<ChordiaImage> {

    private val fontResolver = FontResolver()

    actual override fun convert(configuration: Configuration, raw: String): ChordiaImage {
        return ConverterBinding(configuration).convert(raw)
    }

    /**
     * Conversion using AndroidSVG take:
     * - From 85% to 95% of rendering time
     * - From 75% to 85% of whole process
     **/
    private fun ConverterBinding.convert(raw: String): ChordiaImage {
        SVG.registerExternalFileResolver(fontResolver)
        return ChordiaImage(PictureDrawable(SVG.getFromString(raw).renderToPicture()))
            .also { SVG.deregisterExternalFileResolver() }
    }
}

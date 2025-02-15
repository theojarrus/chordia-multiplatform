package com.neotive.chordia.svg.tool

import cocoapods.SVGKit.SVGKImage
import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.rendering.api.model.ChordiaImage
import com.neotive.chordia.svg.api.tool.RenderingSvgConverter
import com.neotive.chordia.svg.configuration.ConverterBinding
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual class BaseRenderingSvgConverter : RenderingSvgConverter<ChordiaImage> {

    actual override fun convert(configuration: Configuration, raw: String): ChordiaImage {
        return ConverterBinding(configuration).convert(raw)
    }

    private fun ConverterBinding.convert(raw: String): ChordiaImage {
        return ChordiaImage(requireNotNull(SVGKImage(raw).UIImage))
    }
}

package com.neotive.chordia.svg.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.rendering.api.model.ChordiaImage
import com.neotive.chordia.svg.api.tool.RenderingSvgConverter

expect class BaseRenderingSvgConverter() : RenderingSvgConverter<ChordiaImage> {

    override fun convert(configuration: Configuration, raw: String): ChordiaImage
}

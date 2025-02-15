package com.neotive.chordia.rendering

import com.neotive.chordia.cache.api.model.CachePolicy
import com.neotive.chordia.configuration.tool.BaseConfigurationManager
import com.neotive.chordia.rendering.api.model.ChordiaImage
import com.neotive.chordia.rendering.api.tool.RenderingProcessor
import com.neotive.chordia.rendering.api.tool.RenderingVisualizer
import com.neotive.chordia.rendering.cache.CacheRenderingProcessor
import com.neotive.chordia.svg.api.tool.RenderingSvgConverter
import com.neotive.chordia.svg.tool.BaseRenderingSvgConverter
import com.neotive.chordia.svg.tool.BaseRenderingSvgVisualizer

operator fun <Image : Any> RenderingProcessor.Companion.invoke(
    cachePolicy: CachePolicy,
    visualizer: RenderingVisualizer<Image>
): RenderingProcessor<Image> {
    return CacheRenderingProcessor(
        cachePolicy = cachePolicy,
        visualizer = visualizer,
        configurationManager = BaseConfigurationManager()
    )
}

operator fun <Image : Any> RenderingProcessor.Companion.invoke(
    cachePolicy: CachePolicy,
    converter: RenderingSvgConverter<Image>
): RenderingProcessor<Image> {
    return RenderingProcessor(
        cachePolicy = cachePolicy,
        visualizer = BaseRenderingSvgVisualizer(converter = converter)
    )
}

operator fun RenderingProcessor.Companion.invoke(cachePolicy: CachePolicy): RenderingProcessor<ChordiaImage> {
    return RenderingProcessor(
        cachePolicy = cachePolicy,
        converter = BaseRenderingSvgConverter()
    )
}

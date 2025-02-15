package com.neotive.chordia.rendering

import com.neotive.chordia.configuration.tool.BaseConfigurationManager
import com.neotive.chordia.rendering.api.model.ChordiaImage
import com.neotive.chordia.rendering.api.tool.RenderingProcessor
import com.neotive.chordia.rendering.api.tool.RenderingVisualizer
import com.neotive.chordia.rendering.base.tool.BaseRenderingProcessor
import com.neotive.chordia.svg.api.tool.RenderingSvgConverter
import com.neotive.chordia.svg.tool.BaseRenderingSvgConverter
import com.neotive.chordia.svg.tool.BaseRenderingSvgVisualizer

operator fun <Image> RenderingProcessor.Companion.invoke(
    visualizer: RenderingVisualizer<Image>
): RenderingProcessor<Image> {
    return BaseRenderingProcessor(
        visualizer = visualizer,
        configurationManager = BaseConfigurationManager()
    )
}

operator fun <Image> RenderingProcessor.Companion.invoke(
    converter: RenderingSvgConverter<Image>
): RenderingProcessor<Image> {
    return RenderingProcessor(visualizer = BaseRenderingSvgVisualizer(converter = converter))
}

operator fun RenderingProcessor.Companion.invoke(): RenderingProcessor<ChordiaImage> {
    return RenderingProcessor(converter = BaseRenderingSvgConverter())
}

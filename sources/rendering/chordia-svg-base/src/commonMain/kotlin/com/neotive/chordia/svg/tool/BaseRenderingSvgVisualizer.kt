package com.neotive.chordia.svg.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.rendering.api.model.shape.Shape.Composition
import com.neotive.chordia.rendering.api.tool.RenderingVisualizer
import com.neotive.chordia.svg.api.tool.RenderingSvgConverter
import com.neotive.chordia.svg.api.tool.RenderingSvgEncoder
import com.neotive.chordia.svg.configuration.VisualizerBinding

class BaseRenderingSvgVisualizer<Image>(
    private val converter: RenderingSvgConverter<Image>,
    private val encoder: RenderingSvgEncoder = BaseRenderingSvgEncoder()
) : RenderingVisualizer<Image> {

    override fun visualize(configuration: Configuration, composition: Composition): Image {
        return VisualizerBinding(configuration).visualize(composition)
    }

    private fun VisualizerBinding.visualize(composition: Composition): Image {
        return encoder.encode(configuration, composition)
            .let { converter.convert(configuration, it) }
    }
}

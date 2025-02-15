package com.neotive.chordia.svg.configuration

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.rendering.api.model.shape.Shape.Composition

internal typealias VisualizerBinding = BaseRenderingSvgVisualizerBinding

internal class BaseRenderingSvgVisualizerBinding(
    val configuration: Configuration
) : BaseRenderingSvgConverterConfiguration

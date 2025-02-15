package com.neotive.chordia.rendering.api.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.rendering.api.model.shape.Shape.Composition

interface RenderingVisualizer<Image> {

    fun visualize(configuration: Configuration, composition: Composition): Image
}

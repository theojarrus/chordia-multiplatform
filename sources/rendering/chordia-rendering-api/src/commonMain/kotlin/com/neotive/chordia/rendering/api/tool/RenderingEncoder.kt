package com.neotive.chordia.rendering.api.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.rendering.api.model.shape.Shape.Composition

interface RenderingEncoder<Raw : Any> {

    fun encode(configuration: Configuration, composition: Composition): Raw
}

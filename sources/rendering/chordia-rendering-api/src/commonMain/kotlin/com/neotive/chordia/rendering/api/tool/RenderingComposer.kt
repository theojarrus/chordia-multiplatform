package com.neotive.chordia.rendering.api.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.rendering.api.model.shape.Shape.Composition

interface RenderingComposer {

    fun compose(configuration: Configuration, variant: Variant): Composition
}

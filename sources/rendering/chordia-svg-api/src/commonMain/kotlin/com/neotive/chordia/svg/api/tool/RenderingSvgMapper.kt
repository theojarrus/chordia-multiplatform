package com.neotive.chordia.svg.api.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.rendering.api.model.shape.Shape
import com.neotive.chordia.svg.api.model.SvgShape

interface RenderingSvgMapper {

    fun map(configuration: Configuration, shape: Shape): SvgShape
}

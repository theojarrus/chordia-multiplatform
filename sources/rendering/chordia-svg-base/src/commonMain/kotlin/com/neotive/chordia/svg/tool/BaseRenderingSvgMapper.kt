package com.neotive.chordia.svg.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.rendering.api.model.shape.Shape
import com.neotive.chordia.rendering.api.model.shape.Shape.Circle
import com.neotive.chordia.rendering.api.model.shape.Shape.Composition
import com.neotive.chordia.rendering.api.model.shape.Shape.Line
import com.neotive.chordia.rendering.api.model.shape.Shape.Rect
import com.neotive.chordia.rendering.api.model.shape.Shape.Text
import com.neotive.chordia.svg.api.model.SvgShape
import com.neotive.chordia.svg.api.model.SvgShape.SvgBlock
import com.neotive.chordia.svg.api.model.SvgShape.SvgCircle
import com.neotive.chordia.svg.api.model.SvgShape.SvgLine
import com.neotive.chordia.svg.api.model.SvgShape.SvgRect
import com.neotive.chordia.svg.api.model.SvgShape.SvgText
import com.neotive.chordia.svg.api.tool.RenderingSvgMapper
import com.neotive.chordia.svg.configuration.MapperBinding

class BaseRenderingSvgMapper : RenderingSvgMapper {

    override fun map(configuration: Configuration, shape: Shape): SvgShape {
        return MapperBinding(configuration).map(shape)
    }

    private fun MapperBinding.map(shape: Shape): SvgShape {
        return when (shape) {
            is Composition -> SvgBlock(shape, shape.shapes.map { map(configuration, it) })
            is Circle -> SvgCircle(shape)
            is Line -> SvgLine(shape)
            is Rect -> SvgRect(shape)
            is Text -> SvgText(shape)
        }
    }
}

package com.neotive.chordia.svg.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.rendering.api.model.shape.Shape.Composition
import com.neotive.chordia.svg.api.model.SvgShape
import com.neotive.chordia.svg.api.tool.RenderingSvgEncoder
import com.neotive.chordia.svg.api.tool.RenderingSvgMapper
import com.neotive.chordia.svg.configuration.EncoderBinding

class BaseRenderingSvgEncoder(
    private val mapper: RenderingSvgMapper = BaseRenderingSvgMapper()
) : RenderingSvgEncoder {

    override fun encode(configuration: Configuration, composition: Composition): String {
        return EncoderBinding(configuration).encode(composition)
    }

    private fun EncoderBinding.encode(composition: Composition): String {
        return mapper.map(configuration, composition)
            .let(::encodeShape)
    }

    private fun encodeShape(shape: SvgShape): String {
        return buildShape(shape)
            .filterNot(String::isEmpty)
            .joinToString(separator = " ", postfix = "\n")
    }

    private fun buildShape(shape: SvgShape): List<String> {
        return buildList {
            add("<${shape.tag}")
            shape.arguments.forEach { argument -> add("${argument.name}=\"${argument.value}\"") }
            add(">")
            add(shape.raw)
            shape.shapes.forEach { shape -> add(encodeShape(shape)) }
            add("</${shape.tag}>")
        }
    }
}

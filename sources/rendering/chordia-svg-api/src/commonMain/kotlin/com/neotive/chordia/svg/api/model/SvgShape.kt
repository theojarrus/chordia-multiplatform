package com.neotive.chordia.svg.api.model

import com.neotive.chordia.svg.api.model.SvgArgument.*
import com.neotive.chordia.rendering.api.model.shape.Shape.Circle
import com.neotive.chordia.rendering.api.model.shape.Shape.Composition
import com.neotive.chordia.rendering.api.model.shape.Shape.Line
import com.neotive.chordia.rendering.api.model.shape.Shape.Rect
import com.neotive.chordia.rendering.api.model.shape.Shape.Text
import com.neotive.chordia.rendering.api.model.shape.Style.FILL
import com.neotive.chordia.rendering.api.model.shape.Style.STROKE

sealed class SvgShape(
    val tag: String,
    val arguments: Set<SvgArgument>,
    open val shapes: List<SvgShape> = emptyList(),
    open val raw: String = ""
) {

    data class SvgCircle(val circle: Circle) : SvgShape(
        tag = "circle",
        arguments = setOf(
            Variable("cx", circle.centerX),
            Variable("cy", circle.centerY),
            Variable("r", circle.radius),
            StrokeWidth(circle.weight),
            Filling.get(circle.style, circle.color)
        )
    )

    data class SvgLine(val line: Line) : SvgShape(
        tag = "line",
        arguments = setOf(
            Variable("x1", line.startX),
            Variable("y1", line.startY),
            Variable("x2", line.endX),
            Variable("y2", line.endY),
            StrokeWidth(line.weight),
            StrokeLinecap.get(line.corners),
            Filling.get(STROKE, line.color)
        )
    )

    data class SvgRect(val rect: Rect) : SvgShape(
        tag = "rect",
        arguments = setOf(
            Variable("x", rect.startX),
            Variable("y", rect.startY),
            Variable("rx", rect.radius),
            Width(rect.width),
            Height(rect.height),
            StrokeWidth(rect.weight),
            Filling.get(rect.style, rect.color)
        )
    )

    data class SvgText(val text: Text) : SvgShape(
        tag = "text",
        arguments = setOf(
            Variable("x", text.x),
            Variable("y", text.y),
            FontSize(text.size),
            FontAlignment.Center,
            FontFaceFamily(text.font.family),
            FontFaceWeight(text.font.weight),
            FontFaceStyle.get(text.font.style),
            TextAnchor.get(text.align),
            Filling.get(FILL, text.color)
        ),
        raw = text.text
    )

    data class SvgBlock(
        val shape: Composition,
        override val shapes: List<SvgShape>,
    ) : SvgShape(
        tag = "svg",
        arguments = setOf(
            Namespace,
            Width(shape.width),
            Height(shape.height),
            ViewBox(shape.width, shape.height, shape.x, shape.y),
            Filling.Fill(null),
            Overflow.Visible
        )
    )
}

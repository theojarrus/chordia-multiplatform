package com.neotive.chordia.rendering.base.tool.strings

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Type.Strings
import com.neotive.chordia.core.model.Point
import com.neotive.chordia.core.model.Position
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.rendering.api.model.shape.Align.CENTER
import com.neotive.chordia.rendering.api.model.shape.Align.START
import com.neotive.chordia.rendering.api.model.shape.Color
import com.neotive.chordia.rendering.api.model.shape.Corners.ROUND
import com.neotive.chordia.rendering.api.model.shape.Corners.SQUARE
import com.neotive.chordia.rendering.api.model.shape.Shape
import com.neotive.chordia.rendering.api.model.shape.Shape.Circle
import com.neotive.chordia.rendering.api.model.shape.Shape.Composition
import com.neotive.chordia.rendering.api.model.shape.Shape.Composition.Cross
import com.neotive.chordia.rendering.api.model.shape.Shape.Line
import com.neotive.chordia.rendering.api.model.shape.Shape.Rect
import com.neotive.chordia.rendering.api.model.shape.Shape.Text
import com.neotive.chordia.rendering.api.model.shape.Style
import com.neotive.chordia.rendering.api.model.shape.Style.FILL
import com.neotive.chordia.rendering.api.model.shape.Style.STROKE
import com.neotive.chordia.rendering.api.tool.RenderingComposerDelegate
import com.neotive.chordia.rendering.configuration.strings.StringsComposerBinding

class BaseRenderingStringsComposer : RenderingComposerDelegate {

    override fun isResponsible(configuration: Configuration): Boolean {
        return configuration.parameters.instrument.type is Strings
    }

    override fun compose(configuration: Configuration, variant: Variant): Composition {
        return StringsComposerBinding(configuration).compose(variant)
    }

    private fun StringsComposerBinding.compose(variant: Variant): Composition {
        return Composition(
            width = configuration.parameters.instrument.lines.lastIndex + sidePadding,
            height = rows + sidePadding,
            shapes = composeShapes(variant)
        ).scaled(scale)
    }

    private fun StringsComposerBinding.composeShapes(variant: Variant): List<Shape> {
        return buildList {
            addAll(composeBase())
            addAll(composeNotes(variant))
        }
    }

    private fun StringsComposerBinding.composeBase(): List<Shape> {
        return buildList {
            val columns = configuration.parameters.instrument.lines.lastIndex
            add(getBaseRect(columns = columns, color = theme.backgroundColor, style = FILL))
            addAll((1 until rows).map { y -> getBaseHorizontalLine(y = y, endX = columns) })
            addAll((1 until columns).map { getBaseVerticalLine(it) })
            add(getBaseRect(columns = columns, color = theme.primaryColor, style = STROKE))
        }
    }

    private fun StringsComposerBinding.composeNotes(variant: Variant): List<Shape> {
        return buildList {
            val firstPosition = variant.getFirstPosition()
            add(getHint(firstPosition.toString()))
            addAll(composeRows(variant, firstPosition))
            addAll(composeClosedLines(variant))
            variant.points.forEach { point ->
                if (point.position == Position.OPEN.value) {
                    add(getOpenedNote(point.line))
                } else {
                    addAll(composeNote(point, firstPosition))
                }
            }
        }
    }

    private fun StringsComposerBinding.composeRows(variant: Variant, firstPosition: Int): List<Shape> {
        return variant.rows
            .map { row ->
                getRowRect(
                    y = getFramedPosition(position = row.position, firstPosition = firstPosition),
                    startX = row.start,
                    endX = row.end
                )
            }
    }

    private fun StringsComposerBinding.composeClosedLines(variant: Variant): List<Shape> {
        return variant.lines
            .run {
                configuration.parameters.instrument.lines.indices
                    .filterNot(::contains)
                    .map { getClosedLine(it) }
            }
    }

    private fun StringsComposerBinding.composeNote(point: Point, firstPosition: Int): List<Shape> {
        return buildList {
            val position = getFramedPosition(position = point.position, firstPosition = firstPosition)
            add(getNote(x = point.line, y = position))
            point.hint?.let { hint -> add(getNoteHint(x = point.line, y = position, hint = hint.toString())) }
        }
    }

    private fun StringsComposerBinding.getBaseRect(columns: Int, color: Color, style: Style): Shape {
        return Rect(
            startX = padding,
            startY = padding,
            width = columns.toFloat(),
            height = rows.toFloat(),
            weight = weight,
            radius = radius,
            color = color,
            style = style
        )
    }

    private fun StringsComposerBinding.getBaseHorizontalLine(y: Int, endX: Int): Shape {
        return Line(
            startX = padding,
            startY = padding + y,
            endX = padding + endX,
            endY = padding + y,
            weight = weight,
            color = theme.secondaryColor,
            corners = SQUARE
        )
    }

    private fun StringsComposerBinding.getBaseVerticalLine(x: Int): Shape {
        return Line(
            startX = padding + x,
            startY = padding,
            endX = padding + x,
            endY = padding + rows,
            weight = weight,
            color = theme.primaryColor,
            corners = SQUARE
        )
    }

    private fun StringsComposerBinding.getHint(hint: String): Shape {
        return Text(
            x = padding - FULL,
            y = padding + HALF,
            size = textSize,
            text = hint,
            font = font,
            color = theme.primaryColor,
            align = START
        )
    }

    private fun StringsComposerBinding.getRowRect(y: Int, startX: Int, endX: Int): Shape {
        return Rect(
            startX = padding + startX,
            startY = padding + y + HALF - noteSize,
            width = (endX - startX).toFloat(),
            height = noteSize * 2,
            weight = weight,
            radius = 0f,
            color = theme.primaryColor,
            style = FILL
        )
    }

    private fun StringsComposerBinding.getClosedLine(x: Int): Shape {
        val size = floatingSize * 0.9f
        return Cross(
            startX = padding + x - size,
            startY = padding - FULL + floatingPadding + (floatingSize - size),
            size = size * 2,
            weight = weight,
            color = theme.primaryColor,
            corners = ROUND
        )
    }

    private fun StringsComposerBinding.getOpenedNote(x: Int): Shape {
        return Circle(
            centerX = padding + x,
            centerY = padding - FULL + floatingPadding + floatingSize,
            radius = floatingSize,
            weight = weight,
            color = theme.primaryColor,
            style = STROKE
        )
    }

    private fun StringsComposerBinding.getNote(x: Int, y: Int): Shape {
        return Circle(
            centerX = padding + x,
            centerY = padding + y + HALF,
            radius = noteSize,
            weight = 0f,
            color = theme.primaryColor,
            style = FILL
        )
    }

    private fun StringsComposerBinding.getNoteHint(x: Int, y: Int, hint: String): Shape {
        return Text(
            x = padding + x,
            y = padding + y + HALF,
            size = textSize,
            text = hint,
            font = font,
            color = theme.colorOnPrimary,
            align = CENTER
        )
    }

    private fun StringsComposerBinding.getFramedPosition(position: Int, firstPosition: Int): Int {
        return (position - firstPosition) % rows
    }

    private fun Variant.getFirstPosition(): Int {
        return points
            .filter { it.position != Position.OPEN.value }
            .minOfOrNull(Point::position)
            ?: Position.FIRST.value
    }

    companion object {

        private const val FULL = 1f
        private const val HALF = 0.5f
    }
}

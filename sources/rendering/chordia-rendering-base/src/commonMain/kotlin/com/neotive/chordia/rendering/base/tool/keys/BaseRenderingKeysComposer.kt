package com.neotive.chordia.rendering.base.tool.keys

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Type.Keys
import com.neotive.chordia.core.model.Point
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.rendering.api.model.keys.Tones
import com.neotive.chordia.rendering.api.model.shape.Align.CENTER
import com.neotive.chordia.rendering.api.model.shape.Color
import com.neotive.chordia.rendering.api.model.shape.Corners.SQUARE
import com.neotive.chordia.rendering.api.model.shape.Shape
import com.neotive.chordia.rendering.api.model.shape.Shape.Circle
import com.neotive.chordia.rendering.api.model.shape.Shape.Composition
import com.neotive.chordia.rendering.api.model.shape.Shape.Line
import com.neotive.chordia.rendering.api.model.shape.Shape.Rect
import com.neotive.chordia.rendering.api.model.shape.Shape.Text
import com.neotive.chordia.rendering.api.model.shape.Style
import com.neotive.chordia.rendering.api.model.shape.Style.FILL
import com.neotive.chordia.rendering.api.model.shape.Style.STROKE
import com.neotive.chordia.rendering.api.tool.RenderingComposerDelegate
import com.neotive.chordia.rendering.api.tool.keys.RenderingKeysToneSeparator
import com.neotive.chordia.rendering.configuration.keys.KeysComposerBinding

class BaseRenderingKeysComposer(
    private val toneSeparator: RenderingKeysToneSeparator = BaseRenderingKeysToneSeparator()
) : RenderingComposerDelegate {

    override fun isResponsible(configuration: Configuration): Boolean {
        return configuration.parameters.instrument.type is Keys
    }

    override fun compose(configuration: Configuration, variant: Variant): Composition {
        return KeysComposerBinding(configuration).compose(variant)
    }

    private fun KeysComposerBinding.compose(variant: Variant): Composition {
        val tones = toneSeparator.separate(configuration, variant)
        return Composition(
            width = tones.fulltones.size + sidePadding,
            height = rows + sidePadding,
            shapes = composeShapes(tones = tones, variant = variant)
        ).scaled(scale)
    }

    private fun KeysComposerBinding.composeShapes(tones: Tones, variant: Variant): List<Shape> {
        return buildList {
            addAll(composeBase(tones))
            addAll(composeNotes(tones, variant))
        }
    }

    private fun KeysComposerBinding.composeBase(tones: Tones): List<Shape> {
        return buildList {
            add(getBaseRect(columns = tones.fulltones.size, color = theme.backgroundColor, style = FILL))
            addAll((1 until tones.fulltones.size).map { getBaseVerticalLine(it) })
            addAll(composeBaseHalftones(tones))
            add(getBaseRect(columns = tones.fulltones.size, color = theme.primaryColor, style = STROKE))
        }
    }

    private fun KeysComposerBinding.composeBaseHalftones(tones: Tones): List<Shape> {
        return buildList {
            tones.halftones
                .forEach { tone ->
                    val position = tones.shifted(tone.position)
                    add(getBaseHalftoneRect(position))
                    add(getBaseHalftoneOffsetRect(position))
                }
        }
    }

    private fun KeysComposerBinding.composeNotes(tones: Tones, variant: Variant): List<Shape> {
        return variant.points
            .flatMap { point ->
                if (point.note.isHalftone) {
                    composeHalftoneNote(tones, point)
                } else {
                    composeFulltoneNote(tones, point)
                }
            }
    }

    private fun KeysComposerBinding.composeHalftoneNote(tones: Tones, point: Point): List<Shape> {
        return buildList {
            val position = tones.shifted(point.line)
            add(getHalftoneNote(position))
            point.hint?.let { hint -> add(getHalftoneNoteHint(x = position, hint = hint)) }
        }
    }

    private fun KeysComposerBinding.composeFulltoneNote(tones: Tones, point: Point): List<Shape> {
        return buildList {
            val position = tones.shifted(point.line)
            add(getFulltoneNote(position))
            point.hint?.let { hint -> add(getFulltoneNoteHint(x = position, hint = hint)) }
        }
    }

    private fun KeysComposerBinding.getBaseRect(columns: Int, color: Color, style: Style): Shape {
        return Rect(
            startX = padding,
            startY = padding,
            width = columns.toFloat(),
            weight = weight,
            height = rows.toFloat(),
            radius = radius,
            color = color,
            style = style
        )
    }

    private fun KeysComposerBinding.getBaseVerticalLine(x: Int): Shape {
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

    private fun KeysComposerBinding.getBaseHalftoneRect(x: Int): Shape {
        return Rect(
            startX = padding + halftonePadding + x - HALF,
            startY = padding,
            width = halftoneWidth,
            height = halftoneHeight,
            weight = weight,
            radius = radius,
            color = theme.primaryColor,
            style = FILL
        )
    }

    private fun KeysComposerBinding.getBaseHalftoneOffsetRect(x: Int): Shape {
        return Rect(
            startX = padding + halftonePadding + x - HALF,
            startY = padding,
            width = halftoneWidth,
            height = radius,
            weight = weight,
            radius = 0f,
            color = theme.primaryColor,
            style = FILL
        )
    }

    private fun KeysComposerBinding.getHalftoneNote(x: Int): Shape {
        return Circle(
            centerX = padding + x,
            centerY = padding + halftoneHeight - HALF,
            radius = noteSize,
            weight = 0f,
            color = theme.colorOnPrimary,
            style = FILL
        )
    }

    private fun KeysComposerBinding.getHalftoneNoteHint(x: Int, hint: Int): Shape {
        return Text(
            x = padding + x,
            y = padding + halftoneHeight - HALF,
            size = textSize,
            text = hint.toString(),
            font = font,
            color = theme.primaryColor,
            align = CENTER
        )
    }

    private fun KeysComposerBinding.getFulltoneNote(x: Int): Shape {
        return Circle(
            centerX = padding + x + HALF,
            centerY = padding + rows - HALF,
            radius = noteSize,
            weight = 0f,
            color = theme.primaryColor,
            style = FILL
        )
    }

    private fun KeysComposerBinding.getFulltoneNoteHint(x: Int, hint: Int): Shape {
        return Text(
            x = padding + x + HALF,
            y = padding + rows - HALF,
            size = textSize,
            text = hint.toString(),
            font = font,
            color = theme.colorOnPrimary,
            align = CENTER
        )
    }

    companion object {

        private const val HALF = 0.5f
    }
}

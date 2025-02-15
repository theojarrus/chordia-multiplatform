package com.neotive.chordia.svg.api.model

import com.neotive.chordia.rendering.api.model.shape.Align
import com.neotive.chordia.rendering.api.model.shape.Align.CENTER
import com.neotive.chordia.rendering.api.model.shape.Align.START
import com.neotive.chordia.rendering.api.model.shape.Color
import com.neotive.chordia.rendering.api.model.shape.Corners
import com.neotive.chordia.rendering.api.model.shape.Corners.SQUARE
import com.neotive.chordia.rendering.api.model.shape.Corners.ROUND
import com.neotive.chordia.rendering.api.model.shape.FontFamily
import com.neotive.chordia.rendering.api.model.shape.FontStyle
import com.neotive.chordia.rendering.api.model.shape.FontStyle.NORMAL
import com.neotive.chordia.rendering.api.model.shape.FontStyle.ITALIC
import com.neotive.chordia.rendering.api.model.shape.FontWeight
import com.neotive.chordia.rendering.api.model.shape.HexColor.TRANSPARENT
import com.neotive.chordia.rendering.api.model.shape.Style
import com.neotive.chordia.rendering.api.model.shape.Style.FILL
import com.neotive.chordia.rendering.api.model.shape.Style.STROKE

sealed class SvgArgument(val name: String, val value: Any) {

    data object Namespace : SvgArgument(
        name = "xmlns",
        value = "http://www.w3.org/2000/svg"
    )

    class Variable(name: String, value: Any) : SvgArgument(
        name = name,
        value = value
    )

    class Width(value: Float) : SvgArgument(
        name = "width",
        value = value
    )

    class Height(value: Float) : SvgArgument(
        name = "height",
        value = value
    )

    class StrokeWidth(value: Float) : SvgArgument(
        name = "stroke-width",
        value = value
    )

    class FontSize(value: Float) : SvgArgument(
        name = "font-size",
        value = value
    )

    class FontFaceFamily(family: FontFamily) : SvgArgument(
        name = "font-family",
        value = family.name
    )

    class FontFaceWeight(weight: FontWeight) : SvgArgument(
        name = "font-weight",
        value = weight.value
    )

    class ViewBox(width: Float, height: Float, x: Float = 0f, y: Float = 0f) : SvgArgument(
        name = "viewBox",
        value = "-$x -$y $width $height"
    )

    sealed class Overflow(value: String) : SvgArgument(
        name = "overflow",
        value = value
    ) {

        data object Visible : Overflow("visible")
        data object Hidden : Overflow("hidden")
    }

    sealed class FontAlignment(value: String) : SvgArgument(
        name = "dy",
        value = value
    ) {

        data object Default : FontAlignment("0em")
        data object Center : FontAlignment(".35em")
    }

    sealed class Filling(name: String, value: Color?) : SvgArgument(
        name = name,
        value = value?.code ?: TRANSPARENT
    ) {

        class Fill(value: Color?) : Filling(name = "fill", value = value)
        class Stroke(value: Color?) : Filling(name = "stroke", value = value)

        companion object {

            fun get(style: Style, color: Color): Filling {
                return when (style) {
                    FILL -> Fill(color)
                    STROKE -> Stroke(color)
                }
            }
        }
    }

    sealed class FontFaceStyle(value: String) : SvgArgument(
        name = "font-style",
        value = value
    ) {

        data object Normal : FontFaceStyle("normal")
        data object Italic : FontFaceStyle("italic")

        companion object {

            fun get(style: FontStyle): FontFaceStyle {
                return when (style) {
                    NORMAL -> Normal
                    ITALIC -> Italic
                }
            }
        }
    }

    sealed class TextAnchor(value: String) : SvgArgument(
        name = "text-anchor",
        value = value
    ) {

        data object Start : TextAnchor(value = "start")
        data object Middle : TextAnchor(value = "middle")

        companion object {

            fun get(align: Align): TextAnchor {
                return when (align) {
                    START -> Start
                    CENTER -> Middle
                }
            }
        }
    }

    sealed class StrokeLinecap(value: String) : SvgArgument(name = "stroke-linecap", value = value) {

        data object Round : StrokeLinecap("round")
        data object Butt : StrokeLinecap("butt")

        companion object {

            fun get(corners: Corners): StrokeLinecap {
                return when (corners) {
                    SQUARE -> Butt
                    ROUND -> Round
                }
            }
        }
    }
}

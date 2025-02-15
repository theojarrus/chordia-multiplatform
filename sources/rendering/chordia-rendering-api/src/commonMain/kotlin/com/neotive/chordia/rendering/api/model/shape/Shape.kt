package com.neotive.chordia.rendering.api.model.shape

sealed interface Shape {

    data class Circle(
        val centerX: Float,
        val centerY: Float,
        val radius: Float,
        val weight: Float,
        val color: Color,
        val style: Style
    ) : Shape {

        override fun scaled(number: Float): Circle {
            return copy(
                centerX = centerX * number,
                centerY = centerY * number,
                radius = radius * number,
                weight = weight * number
            )
        }
    }

    data class Line(
        val startX: Float,
        val startY: Float,
        val endX: Float,
        val endY: Float,
        val weight: Float,
        val color: Color,
        val corners: Corners
    ) : Shape {

        override fun scaled(number: Float): Line {
            return copy(
                startX = startX * number,
                startY = startY * number,
                endX = endX * number,
                endY = endY * number,
                weight = weight * number
            )
        }
    }

    data class Rect(
        val startX: Float,
        val startY: Float,
        val width: Float,
        val height: Float,
        val weight: Float,
        val radius: Float,
        val color: Color,
        val style: Style
    ) : Shape {

        override fun scaled(number: Float): Rect {
            return copy(
                startX = startX * number,
                startY = startY * number,
                width = width * number,
                height = height * number,
                weight = weight * number,
                radius = radius * number
            )
        }
    }

    data class Text(
        val x: Float,
        val y: Float,
        val size: Float,
        val text: String,
        val font: Font,
        val color: Color,
        val align: Align
    ) : Shape {

        override fun scaled(number: Float): Text {
            return copy(
                x = x * number,
                y = y * number,
                size = size * number
            )
        }
    }

    open class Composition(
        val width: Float,
        val height: Float,
        val shapes: List<Shape>,
        val x: Float = 0f,
        val y: Float = 0f,
    ) : Shape {

        data class Cross(
            val startX: Float,
            val startY: Float,
            val size: Float,
            val weight: Float,
            val color: Color,
            val corners: Corners
        ) : Composition(
            width = size,
            height = size,
            x = startX,
            y = startY,
            shapes = listOf(
                Line(
                    startX = 0f,
                    startY = 0f,
                    endX = size,
                    endY = size,
                    weight = weight,
                    color = color,
                    corners = corners
                ),
                Line(
                    startX = size,
                    startY = 0f,
                    endX = 0f,
                    endY = size,
                    weight = weight,
                    color = color,
                    corners = corners
                )
            )
        )

        override fun scaled(number: Float): Composition {
            return Composition(
                width = width * number,
                height = height * number,
                shapes = shapes.map { it.scaled(number) },
                x = x * number,
                y = y * number
            )
        }
    }

    fun scaled(number: Float): Shape
}

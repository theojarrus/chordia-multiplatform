package com.neotive.chordia.generation.base.tool.strings

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Point
import com.neotive.chordia.core.model.Position.OPEN
import com.neotive.chordia.core.model.Row
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.generation.api.tool.strings.GenerationStringsRowsFinder
import com.neotive.chordia.generation.configuration.strings.StringsRowsFinderBinding
import com.neotive.chordia.utils.compareAll
import com.neotive.chordia.utils.filterByCompare
import kotlin.math.max

class BaseGenerationStringsRowsFinder : GenerationStringsRowsFinder {

    override fun getRows(configuration: Configuration, variant: Variant): List<Row> {
        return StringsRowsFinderBinding(configuration).getRows(variant)
    }

    private fun StringsRowsFinderBinding.getRows(variant: Variant): List<Row> {
        return variant.points
            .filterNot { it.position == OPEN.value }
            .takeIf {
                listOf(
                    { filterOverlimited(it) },
                    { filterCompleted(it) }
                ).all { it() }
            }
            ?.let { getRowOrNull(it) }
            .let(::listOfNotNull)
    }

    private fun StringsRowsFinderBinding.getRowOrNull(points: List<Point>): Row? {
        return points
            .groupBy(Point::position)
            .minByOrNull(Map.Entry<Int, *>::key)
            ?.takeIf { filterRowSize(it.value) }
            ?.let { (position, rowPoints) ->
                Row(
                    position = position,
                    start = rowPoints.minByOrNull(Point::line)?.line ?: 0,
                    end = rowPoints.maxByOrNull(Point::line)?.line ?: 0
                )
            }
            ?.takeIf { filterRowPositions(points, it) }
    }

    private fun StringsRowsFinderBinding.filterOverlimited(points: List<Point>): Boolean {
        return points
            .count { it.position != OPEN.value }
            .let { it > maxFingers }
    }

    private fun StringsRowsFinderBinding.filterCompleted(points: List<Point>): Boolean {
        return points.maxOfOrNull(Point::line) == configuration.parameters.instrument.lines.size.dec()
    }

    private fun StringsRowsFinderBinding.filterRowSize(points: List<Point>): Boolean {
        return points.size >= minRowLength
    }

    private fun StringsRowsFinderBinding.filterRowPositions(points: List<Point>, row: Row): Boolean {
        return points
            .sortedBy(Point::line)
            .compareAll { a, b -> a.line < row.start || a.line.inc() == b.line }
    }
}

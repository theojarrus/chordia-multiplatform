package com.neotive.chordia.generation.base.tool.strings

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Type.Strings
import com.neotive.chordia.core.model.Point
import com.neotive.chordia.core.model.Position.OPEN
import com.neotive.chordia.core.model.Row
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.generation.api.tool.GenerationTabulatorDelegate
import com.neotive.chordia.generation.configuration.strings.StringsTabulatorBinding
import kotlin.math.abs

class BaseGenerationStringsTabulator : GenerationTabulatorDelegate {

    override fun isResponsible(configuration: Configuration): Boolean {
        return configuration.parameters.instrument.type is Strings
    }

    override fun tabulate(configuration: Configuration, variants: List<Variant>): List<Variant> {
        return StringsTabulatorBinding(configuration).tabulatnew(variants)
    }

    private fun StringsTabulatorBinding.tabulatnew(variants: List<Variant>): List<Variant> {
        return variants.map { variant ->
            variant.points
                .filterNot { it.position == OPEN.value }
                .sortedBy(Point::position)
                .let { points ->
                    var counter = 1
                    var offset = 0
                    points
                        .asSequence()
                        .mapIndexed { index, point ->
                            val row = variant.rows.associateBy(Row::position)[point.position]
                            when {
                                point.line == row?.start -> {
                                    offset = getOffset(points, point, index, offset)
                                    point.copy(hint = counter + offset)
                                }
                                point.line == row?.end -> {
                                    point.copy(hint = counter + offset)
                                        .also { counter = counter.inc() }
                                }
                                row != null -> {
                                    point.copy(hint = counter + offset)
                                }
                                else -> {
                                    offset = getOffset(points, point, index, offset)
                                    point.copy(hint = counter + offset)
                                        .also { counter = counter.inc() }
                                }
                            }
                        }
                        .toList()
                        .let { variant.copy(points = it) }
                }
        }
    }

    private fun StringsTabulatorBinding.getOffset(points: List<Point>, point: Point, index: Int, offset: Int): Int {
        return points
            .takeIf { points.size < maxFingers }
            ?.getOrNull(index.dec())
            ?.takeIf { abs(it.position - point.position) > POSITION_RANGE_ADVANCEABLE }
            ?.let { offset.inc() }
            ?: offset
    }

    companion object {

        private const val POSITION_RANGE_ADVANCEABLE = 1
    }
}

package com.neotive.chordia.generation.base.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Point
import com.neotive.chordia.core.model.Position.OPEN
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.generation.api.tool.GenerationSorter
import com.neotive.chordia.generation.configuration.SorterBinding

class BaseGenerationSorter : GenerationSorter {

    override fun sort(configuration: Configuration, variants: List<Variant>): List<Variant> {
        return SorterBinding(configuration).sort(variants)
    }

    private fun SorterBinding.sort(variants: List<Variant>): List<Variant> {
        return variants
            .sortedByDescending { variant ->
                variant.points
                    .count { it.position == OPEN.value }
            }
            .sortedBy { variant ->
                variant.points
                    .filter { it.position != OPEN.value }
                    .minOfOrNull(Point::position)
            }
    }
}

package com.neotive.chordia.generation.base.tool.strings

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Instrument
import com.neotive.chordia.core.model.Point
import com.neotive.chordia.core.model.Position.OPEN
import com.neotive.chordia.core.model.Row
import com.neotive.chordia.core.model.Type.Strings
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.generation.api.model.Chord
import com.neotive.chordia.generation.api.tool.GenerationValidatorDelegate
import com.neotive.chordia.generation.api.tool.strings.GenerationStringsRowsFinder
import com.neotive.chordia.generation.configuration.strings.StringsValidatorBinding
import com.neotive.chordia.utils.compareAll
import kotlin.math.abs

class BaseGenerationStringsValidator(
    private val rowsFinder: GenerationStringsRowsFinder = BaseGenerationStringsRowsFinder()
) : GenerationValidatorDelegate {

    override fun isResponsible(configuration: Configuration): Boolean {
        return configuration.parameters.instrument.type is Strings
    }

    override fun isValid(configuration: Configuration, chord: Chord, variant: Variant): Boolean {
        return StringsValidatorBinding(configuration).isValid(configuration.parameters.instrument, chord, variant)
    }

    override fun getOrNull(configuration: Configuration, chord: Chord, variant: Variant): Variant? {
        return StringsValidatorBinding(configuration).getOrNull(chord, variant)
    }

    private fun StringsValidatorBinding.isValid(
        instrument: Instrument,
        chord: Chord,
        variant: Variant
    ): Boolean {
        return listOf(
            { filterFirstTonic(instrument, chord, variant) },
            { filterOpenedPositions(variant) },
            { filterAbsoluteRange(variant) },
            { filterAdjacentRange(variant) },
        ).all { it() }
    }

    private fun StringsValidatorBinding.getOrNull(chord: Chord, variant: Variant): Variant? {
        return variant
            .takeIf { filterComplete(chord, variant) }
            ?.copy(rows = rowsFinder.getRows(configuration, variant))
            ?.takeIf {
                listOf(
                    { filterLimited(it) }
                ).all { it() }
            }
    }

    private fun StringsValidatorBinding.filterFirstTonic(
        instrument: Instrument,
        chord: Chord,
        variant: Variant
    ): Boolean {
        return !instrument.ordered || variant.points.firstOrNull()?.note == chord.firstOrNull()
    }

    private fun StringsValidatorBinding.filterOpenedPositions(variant: Variant): Boolean {
        return isOpenPositionsEnabled || variant.points.none { it.position == OPEN.value }
    }

    private fun StringsValidatorBinding.filterAbsoluteRange(variant: Variant): Boolean {
        return variant.points
            .filterNot { it.position == OPEN.value }
            .map(Point::position)
            .run { (maxOrNull() ?: 0) - (minOrNull() ?: 0) }
            .let { it <= maxAbsoluteRange }
    }

    private fun StringsValidatorBinding.filterAdjacentRange(variant: Variant): Boolean {
        return variant.points
            .filterNot { it.position == OPEN.value }
            .sortedBy(Point::line)
            .compareAll { a, b -> abs(a.position - b.position) <= maxAdjacentRange }
    }

    private fun StringsValidatorBinding.filterComplete(chord: Chord, variant: Variant): Boolean {
        return variant.notes.containsAll(chord)
    }

    private fun StringsValidatorBinding.filterLimited(variant: Variant): Boolean {
        return variant.points
            .count { it.position != OPEN.value && variant.rows.none { row -> it.position == row.position } }
            .plus(variant.rows.size)
            .let { it <= maxFingers }
    }
}

package com.neotive.chordia.generation.base.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Note
import com.neotive.chordia.core.model.Point
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.generation.configuration.BaseGenerationCombinatorBinding
import com.neotive.chordia.generation.configuration.CombinatorBinding
import com.neotive.chordia.generation.api.model.Chord
import com.neotive.chordia.generation.api.model.LinePositions
import com.neotive.chordia.generation.api.tool.GenerationCombinator
import com.neotive.chordia.generation.api.tool.GenerationIterator
import com.neotive.chordia.generation.api.tool.GenerationValidator
import com.neotive.chordia.utils.ifEmptyOrPlusWithDistinctBy

class BaseGenerationCombinator(
    private val iterator: GenerationIterator = BaseGenerationIterator(),
    private val validator: GenerationValidator = BaseGenerationValidator()
) : GenerationCombinator {

    override fun combine(configuration: Configuration, chord: Chord, positions: LinePositions): List<Variant> {
        return BaseGenerationCombinatorBinding(configuration).combine(chord, positions)
    }

    private fun CombinatorBinding.combine(chord: Chord, positions: LinePositions): List<Variant> {
        return combineVariants(
            chord = chord,
            positions = positions,
            variant = Variant(points = emptyList(), rows = emptyList()),
            line = iterator.start(configuration)
        )
    }

    private fun CombinatorBinding.combineVariants(
        chord: Chord,
        positions: LinePositions,
        variant: Variant,
        line: Int
    ): List<Variant> {
        return configuration.parameters.instrument.lines
            .getOrNull(line)
            ?.let { note ->
                getVariants(positions, variant, line, note)
                    .filter { validator.isValid(configuration, chord, it) }
                    .flatMap { valid ->
                        combineVariants(chord, positions, valid, iterator.next(configuration, line))
                            .ifEmpty { listOfNotNull(validator.getOrNull(configuration, chord, valid)) }
                    }
                    .ifEmptyOrPlusWithDistinctBy(
                        Variant::points,
                        { line == iterator.start(configuration) },
                        { combineVariants(chord, positions, variant, iterator.next(configuration, line)) }
                    )
            }
            .orEmpty()
    }

    private fun CombinatorBinding.getVariants(
        positions: LinePositions,
        variant: Variant,
        line: Int,
        note: Note
    ): List<Variant> {
        return positions[note]
            ?.flatMap { notePositions ->
                notePositions.positions
                    .flatMap { position ->
                        val point = Point(line, notePositions.note, position)
                        listOf(variant.copy(points = variant.points + point))
                    }
            }
            .orEmpty()
    }
}

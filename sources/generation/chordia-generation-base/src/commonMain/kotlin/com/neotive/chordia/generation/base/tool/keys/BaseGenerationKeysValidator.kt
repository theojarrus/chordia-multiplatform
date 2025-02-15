package com.neotive.chordia.generation.base.tool.keys

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Type.Keys
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.generation.api.model.Chord
import com.neotive.chordia.generation.api.tool.GenerationValidatorDelegate
import com.neotive.chordia.generation.configuration.keys.KeysValidatorBinding

class BaseGenerationKeysValidator : GenerationValidatorDelegate {

    override fun isResponsible(configuration: Configuration): Boolean {
        return configuration.parameters.instrument.type is Keys
    }

    override fun isValid(configuration: Configuration, chord: Chord, variant: Variant): Boolean {
        return KeysValidatorBinding(configuration).isValid(chord, variant)
    }

    override fun getOrNull(configuration: Configuration, chord: Chord, variant: Variant): Variant? {
        return KeysValidatorBinding(configuration).getOrNull(chord, variant)
    }

    private fun KeysValidatorBinding.isValid(chord: Chord, variant: Variant): Boolean {
        return listOf(
            variant.points.firstOrNull()?.note == chord.firstOrNull(),
            variant.points.size <= chord.size
        ).all { it }
    }

    private fun KeysValidatorBinding.getOrNull(chord: Chord, variant: Variant): Variant? {
        return variant
            .takeIf { it.notes.containsAll(chord) }
    }
}

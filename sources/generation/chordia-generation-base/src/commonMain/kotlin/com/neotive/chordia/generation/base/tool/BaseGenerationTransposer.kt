package com.neotive.chordia.generation.base.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Note
import com.neotive.chordia.generation.configuration.TransposerBinding
import com.neotive.chordia.generation.api.model.Chord
import com.neotive.chordia.generation.api.tool.GenerationTransposer

class BaseGenerationTransposer : GenerationTransposer {

    override fun transpose(configuration: Configuration): Chord {
        return TransposerBinding(configuration).transpose()
    }

    private fun TransposerBinding.transpose(): Chord = with(configuration.parameters) {
        return Note.entries
            .first()
            .difference(tonic)
            .let { pitch ->
                pattern.notes
                    .distinct()
                    .map { it.pitched(pitch) }
            }
    }
}

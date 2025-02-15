package com.neotive.chordia.generation.base.tool.keys

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Type.Keys
import com.neotive.chordia.core.model.Position.FIRST
import com.neotive.chordia.generation.api.model.Chord
import com.neotive.chordia.generation.api.model.LinePositions
import com.neotive.chordia.generation.api.model.NotePositions
import com.neotive.chordia.generation.api.tool.GenerationParserDelegate
import com.neotive.chordia.generation.configuration.keys.KeysParserBinding

class BaseGenerationKeysParser : GenerationParserDelegate {

    override fun isResponsible(configuration: Configuration): Boolean {
        return configuration.parameters.instrument.type is Keys
    }

    override fun parse(configuration: Configuration, chord: Chord): LinePositions {
        return KeysParserBinding(configuration).parse(chord)
    }

    private fun KeysParserBinding.parse(chord: Chord): LinePositions {
        return configuration.parameters.instrument.lines
            .toSet()
            .filter(chord::contains)
            .associateWith { listOf(NotePositions(note = it, positions = listOf(FIRST.value))) }
    }
}

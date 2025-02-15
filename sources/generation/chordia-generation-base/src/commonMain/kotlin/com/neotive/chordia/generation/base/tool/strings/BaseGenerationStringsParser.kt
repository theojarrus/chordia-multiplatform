package com.neotive.chordia.generation.base.tool.strings

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Type.Strings
import com.neotive.chordia.core.model.Note
import com.neotive.chordia.generation.api.model.Chord
import com.neotive.chordia.generation.api.model.LinePositions
import com.neotive.chordia.generation.api.model.NotePositions
import com.neotive.chordia.generation.api.tool.GenerationParserDelegate
import com.neotive.chordia.generation.configuration.strings.StringsParserBinding
import com.neotive.chordia.utils.plus
import com.neotive.chordia.utils.rangeTo

class BaseGenerationStringsParser : GenerationParserDelegate {

    override fun isResponsible(configuration: Configuration): Boolean {
        return configuration.parameters.instrument.type is Strings
    }

    override fun parse(configuration: Configuration, chord: Chord): LinePositions {
        return StringsParserBinding(configuration).parse(chord)
    }

    private fun StringsParserBinding.parse(chord: Chord): LinePositions = with(configuration.parameters) {
        return instrument.lines
            .toSet()
            .associateWith { line ->
                chord
                    .toSet()
                    .map { note ->
                        val length = instrument.length.value
                        val size = Note.entries.size
                        val difference = line.difference(note)
                        val bottom = length - (length - difference) % size
                        val top = (difference + size.takeIf { difference < 0 }).takeIf { it <= length }
                        NotePositions(note = note, positions = (top..bottom step size).toList())
                    }
            }
    }
}

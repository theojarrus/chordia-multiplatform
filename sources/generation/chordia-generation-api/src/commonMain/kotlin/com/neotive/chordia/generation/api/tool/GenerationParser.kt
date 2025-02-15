package com.neotive.chordia.generation.api.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.generation.api.model.Chord
import com.neotive.chordia.generation.api.model.LinePositions

interface GenerationParser {

    fun parse(configuration: Configuration, chord: Chord): LinePositions
}

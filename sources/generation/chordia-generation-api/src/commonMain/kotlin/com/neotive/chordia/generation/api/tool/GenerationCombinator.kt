package com.neotive.chordia.generation.api.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.generation.api.model.Chord
import com.neotive.chordia.generation.api.model.LinePositions

interface GenerationCombinator {

    fun combine(configuration: Configuration, chord: Chord, positions: LinePositions): List<Variant>
}

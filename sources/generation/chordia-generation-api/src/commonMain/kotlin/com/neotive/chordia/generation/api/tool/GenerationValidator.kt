package com.neotive.chordia.generation.api.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.generation.api.model.Chord

interface GenerationValidator {

    fun isValid(configuration: Configuration, chord: Chord, variant: Variant): Boolean

    fun getOrNull(configuration: Configuration, chord: Chord, variant: Variant): Variant?
}

package com.neotive.chordia.generation.api.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.generation.api.model.Chord

interface GenerationTransposer {

    fun transpose(configuration: Configuration): Chord
}

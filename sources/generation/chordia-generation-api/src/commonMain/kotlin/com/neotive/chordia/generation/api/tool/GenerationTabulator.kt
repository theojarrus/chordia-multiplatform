package com.neotive.chordia.generation.api.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Variant

interface GenerationTabulator {

    fun tabulate(configuration: Configuration, variants: List<Variant>): List<Variant>
}

package com.neotive.chordia.generation.base.tool.keys

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Type.Keys
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.generation.api.tool.GenerationTabulatorDelegate
import com.neotive.chordia.generation.configuration.keys.KeysTabulatorBinding

class BaseGenerationKeysTabulator : GenerationTabulatorDelegate {

    override fun isResponsible(configuration: Configuration): Boolean {
        return configuration.parameters.instrument.type is Keys
    }

    override fun tabulate(configuration: Configuration, variants: List<Variant>): List<Variant> {
        return KeysTabulatorBinding(configuration).tabulate(variants)
    }

    private fun KeysTabulatorBinding.tabulate(variants: List<Variant>): List<Variant> {
        return variants
    }
}

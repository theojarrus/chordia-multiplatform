package com.neotive.chordia.generation.base.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.configuration.tool.ConfigurationDelegator
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.generation.api.tool.GenerationTabulatorDelegate
import com.neotive.chordia.generation.api.tool.GenerationTabulator
import com.neotive.chordia.generation.base.tool.keys.BaseGenerationKeysTabulator
import com.neotive.chordia.generation.base.tool.strings.BaseGenerationStringsTabulator

class BaseGenerationTabulator(
    override val delegates: List<GenerationTabulatorDelegate> = listOf(
        BaseGenerationKeysTabulator(),
        BaseGenerationStringsTabulator()
    )
) : GenerationTabulator, ConfigurationDelegator<GenerationTabulatorDelegate> {

    override fun tabulate(configuration: Configuration, variants: List<Variant>): List<Variant> {
        return getDelegateOrNull(configuration)?.tabulate(configuration, variants).orEmpty()
    }
}

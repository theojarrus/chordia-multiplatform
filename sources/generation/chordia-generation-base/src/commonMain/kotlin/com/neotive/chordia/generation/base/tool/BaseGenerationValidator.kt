package com.neotive.chordia.generation.base.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.configuration.tool.ConfigurationDelegator
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.generation.api.tool.GenerationValidatorDelegate
import com.neotive.chordia.generation.api.model.Chord
import com.neotive.chordia.generation.api.tool.GenerationValidator
import com.neotive.chordia.generation.base.tool.keys.BaseGenerationKeysValidator
import com.neotive.chordia.generation.base.tool.strings.BaseGenerationStringsValidator

class BaseGenerationValidator(
    override val delegates: List<GenerationValidatorDelegate> = listOf(
        BaseGenerationKeysValidator(),
        BaseGenerationStringsValidator()
    )
) : GenerationValidator, ConfigurationDelegator<GenerationValidatorDelegate> {

    override fun isValid(configuration: Configuration, chord: Chord, variant: Variant): Boolean {
        return getDelegate(configuration).isValid(configuration, chord, variant)
    }

    override fun getOrNull(configuration: Configuration, chord: Chord, variant: Variant): Variant? {
        return getDelegate(configuration).getOrNull(configuration, chord, variant)
    }
}

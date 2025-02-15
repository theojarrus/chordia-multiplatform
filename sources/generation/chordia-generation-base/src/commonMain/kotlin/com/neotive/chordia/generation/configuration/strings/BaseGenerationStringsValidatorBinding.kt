package com.neotive.chordia.generation.configuration.strings

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.generation.api.model.Chord

internal typealias StringsValidatorBinding = BaseGenerationStringsValidatorBinding

internal class BaseGenerationStringsValidatorBinding(
    val configuration: Configuration
): BaseGenerationStringsValidatorConfiguration {

    override val maxFingers: Int get() = configuration.get(BaseGenerationStringsValidatorConfiguration.Default::maxFingers)
    override val maxAbsoluteRange: Int get() = configuration.get(BaseGenerationStringsValidatorConfiguration.Default::maxAbsoluteRange)
    override val maxAdjacentRange: Int get() = configuration.get(BaseGenerationStringsValidatorConfiguration.Default::maxAdjacentRange)
    override val isOpenPositionsEnabled: Boolean get() = configuration.get(BaseGenerationStringsValidatorConfiguration.Default::isOpenPositionsEnabled)
}

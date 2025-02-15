package com.neotive.chordia.generation.configuration.keys

import com.neotive.chordia.configuration.model.Configuration

internal typealias KeysValidatorBinding = BaseGenerationKeysValidatorBinding

internal class BaseGenerationKeysValidatorBinding(
    val configuration: Configuration
) : BaseGenerationKeysValidatorConfiguration {

    override val maxFingers: Int get() = configuration.get(BaseGenerationKeysValidatorConfiguration.Default::maxFingers)
    override val maxAbsoluteRange: Int get() = configuration.get(BaseGenerationKeysValidatorConfiguration.Default::maxAbsoluteRange)
    override val maxAdjacentRange: Int get() = configuration.get(BaseGenerationKeysValidatorConfiguration.Default::maxAdjacentRange)
}

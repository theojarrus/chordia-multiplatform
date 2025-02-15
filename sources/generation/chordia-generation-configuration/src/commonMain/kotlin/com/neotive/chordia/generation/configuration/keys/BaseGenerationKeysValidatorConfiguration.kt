package com.neotive.chordia.generation.configuration.keys

import com.neotive.chordia.generation.configuration.BaseGenerationValidatorConfiguration

interface BaseGenerationKeysValidatorConfiguration : BaseGenerationValidatorConfiguration {

    object Default : BaseGenerationKeysValidatorConfiguration {

        override val maxFingers: Int = 10
        override val maxAbsoluteRange: Int = 0
        override val maxAdjacentRange: Int = 0
    }
}

package com.neotive.chordia.generation.configuration.strings

import com.neotive.chordia.generation.configuration.BaseGenerationValidatorConfiguration

interface BaseGenerationStringsValidatorConfiguration : BaseGenerationValidatorConfiguration {

    val isOpenPositionsEnabled: Boolean

    object Default : BaseGenerationStringsValidatorConfiguration {

        override val maxFingers: Int = 4
        override val maxAbsoluteRange: Int = 3
        override val maxAdjacentRange: Int = 3
        override val isOpenPositionsEnabled: Boolean = true
    }
}

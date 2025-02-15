package com.neotive.chordia.generation.configuration.strings

import com.neotive.chordia.generation.configuration.BaseGenerationTabulatorConfiguration

interface BaseGenerationStringsTabulatorConfiguration : BaseGenerationTabulatorConfiguration {

    val maxFingers: Int

    object Default : BaseGenerationStringsTabulatorConfiguration {

        override val maxFingers: Int = 4
    }
}

package com.neotive.chordia.generation.configuration.strings

interface BaseGenerationStringsRowsFinderConfiguration {

    val minRowLength: Int

    object Default : BaseGenerationStringsRowsFinderConfiguration {
        override val minRowLength: Int = 2
    }
}

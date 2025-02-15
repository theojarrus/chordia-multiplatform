package com.neotive.chordia.generation.configuration.strings

import com.neotive.chordia.configuration.model.Configuration

internal typealias StringsRowsFinderBinding = BaseGenerationStringsRowsFinderBinding

internal class BaseGenerationStringsRowsFinderBinding(
    val configuration: Configuration
) : BaseGenerationStringsRowsFinderConfiguration {

    override val minRowLength: Int get() = configuration.get(BaseGenerationStringsRowsFinderConfiguration.Default::minRowLength)
    val maxFingers: Int get() = configuration.get(BaseGenerationStringsValidatorConfiguration.Default::maxFingers)
}

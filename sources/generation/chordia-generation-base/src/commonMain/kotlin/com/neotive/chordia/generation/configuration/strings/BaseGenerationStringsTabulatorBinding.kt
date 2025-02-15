package com.neotive.chordia.generation.configuration.strings

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Variant

internal typealias StringsTabulatorBinding = BaseGenerationStringsTabulatorBinding

internal class BaseGenerationStringsTabulatorBinding(
    val configuration: Configuration
) : BaseGenerationStringsTabulatorConfiguration {

    override val maxFingers: Int get() = configuration.get(BaseGenerationStringsTabulatorConfiguration.Default::maxFingers)
}

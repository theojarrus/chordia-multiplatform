package com.neotive.chordia.generation.configuration.keys

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Variant

internal typealias KeysTabulatorBinding = BaseGenerationKeysTabulatorBinding

internal class BaseGenerationKeysTabulatorBinding(
    val configuration: Configuration
) : BaseGenerationKeysTabulatorConfiguration

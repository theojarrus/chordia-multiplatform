package com.neotive.chordia.generation.configuration.strings

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.generation.api.model.Chord

internal typealias StringsParserBinding = BaseGenerationStringsParserBinding

internal class BaseGenerationStringsParserBinding(
    val configuration: Configuration
) : BaseGenerationStringsParserConfiguration

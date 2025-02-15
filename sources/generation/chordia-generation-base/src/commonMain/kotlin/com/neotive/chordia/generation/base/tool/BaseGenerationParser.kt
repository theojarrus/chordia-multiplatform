package com.neotive.chordia.generation.base.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.configuration.tool.ConfigurationDelegator
import com.neotive.chordia.generation.api.model.Chord
import com.neotive.chordia.generation.api.model.LinePositions
import com.neotive.chordia.generation.api.tool.GenerationParserDelegate
import com.neotive.chordia.generation.api.tool.GenerationParser
import com.neotive.chordia.generation.base.tool.keys.BaseGenerationKeysParser
import com.neotive.chordia.generation.base.tool.strings.BaseGenerationStringsParser

class BaseGenerationParser(
    override val delegates: List<GenerationParserDelegate> = listOf(
        BaseGenerationKeysParser(),
        BaseGenerationStringsParser()
    )
) : GenerationParser, ConfigurationDelegator<GenerationParserDelegate> {

    override fun parse(configuration: Configuration, chord: Chord): LinePositions {
        return getDelegate(configuration).parse(configuration, chord)
    }
}

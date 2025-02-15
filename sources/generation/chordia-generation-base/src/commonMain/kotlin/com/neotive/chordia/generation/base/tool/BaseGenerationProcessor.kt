package com.neotive.chordia.generation.base.tool

import com.neotive.chordia.configuration.tool.ConfigurationManager
import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.generation.api.tool.GenerationCombinator
import com.neotive.chordia.generation.api.tool.GenerationParser
import com.neotive.chordia.generation.api.tool.GenerationProcessor
import com.neotive.chordia.generation.api.tool.GenerationSorter
import com.neotive.chordia.generation.api.tool.GenerationTabulator
import com.neotive.chordia.generation.api.tool.GenerationTransposer
import com.neotive.chordia.generation.configuration.ProcessorBinding

open class BaseGenerationProcessor(
    override val configurationManager: ConfigurationManager,
    private val transposer: GenerationTransposer = BaseGenerationTransposer(),
    private val parser: GenerationParser = BaseGenerationParser(),
    private val combinator: GenerationCombinator = BaseGenerationCombinator(),
    private val tabulator: GenerationTabulator = BaseGenerationTabulator(),
    private val sorter: GenerationSorter = BaseGenerationSorter()
) : GenerationProcessor {

    override fun process(configuration: Configuration): List<Variant> {
        return ProcessorBinding(configuration).process()
    }

    private fun ProcessorBinding.process(): List<Variant> {
        return transposer.transpose(configuration)
            .let { chord ->
                parser.parse(configuration, chord)
                    .let { combinator.combine(configuration, chord, it) }
                    .let { tabulator.tabulate(configuration, it) }
            }
            .let { sorter.sort(configuration, it) }
    }
}

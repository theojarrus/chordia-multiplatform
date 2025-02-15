package com.neotive.chordia.generation.cache

import com.neotive.chordia.cache.api.tool.CacheWrapper
import com.neotive.chordia.cache.api.model.CachePolicy
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.cache.api.tool.CacheFormatter
import com.neotive.chordia.cache.api.tool.CacheManager
import com.neotive.chordia.cache.base.tool.BaseCacheManager
import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.configuration.tool.ConfigurationManager
import com.neotive.chordia.generation.api.tool.GenerationCombinator
import com.neotive.chordia.generation.api.tool.GenerationParser
import com.neotive.chordia.generation.api.tool.GenerationSorter
import com.neotive.chordia.generation.api.tool.GenerationTabulator
import com.neotive.chordia.generation.api.tool.GenerationTransposer
import com.neotive.chordia.generation.base.tool.BaseGenerationCombinator
import com.neotive.chordia.generation.base.tool.BaseGenerationParser
import com.neotive.chordia.generation.base.tool.BaseGenerationProcessor
import com.neotive.chordia.generation.base.tool.BaseGenerationSorter
import com.neotive.chordia.generation.base.tool.BaseGenerationTabulator
import com.neotive.chordia.generation.base.tool.BaseGenerationTransposer

class CacheGenerationProcessor(
    override val cacheManager: CacheManager<Configuration, List<Variant>>?,
    configurationManager: ConfigurationManager,
    transposer: GenerationTransposer = BaseGenerationTransposer(),
    parser: GenerationParser = BaseGenerationParser(),
    combinator: GenerationCombinator = BaseGenerationCombinator(),
    tabulator: GenerationTabulator = BaseGenerationTabulator(),
    private val sorter: GenerationSorter = BaseGenerationSorter()
) : BaseGenerationProcessor(
    configurationManager = configurationManager,
    transposer = transposer,
    parser = parser,
    combinator = combinator,
    tabulator = tabulator,
    sorter = sorter
), CacheWrapper<Configuration, List<Variant>> {

    constructor(
        cachePolicy: CachePolicy,
        configurationManager: ConfigurationManager,
        transposer: GenerationTransposer = BaseGenerationTransposer(),
        parser: GenerationParser = BaseGenerationParser(),
        combinator: GenerationCombinator = BaseGenerationCombinator(),
        tabulator: GenerationTabulator = BaseGenerationTabulator(),
        sorter: GenerationSorter = BaseGenerationSorter(),
        cacheFormatter: CacheFormatter<Configuration> = CacheGenerationFormatter(),
    ) : this(
        cacheManager = BaseCacheManager(cachePolicy, cacheFormatter),
        configurationManager = configurationManager,
        transposer = transposer,
        parser = parser,
        combinator = combinator,
        tabulator = tabulator,
        sorter = sorter
    )

    override fun process(configuration: Configuration): List<Variant> {
        return cached(
            key = configuration,
            factory = { super.process(configuration) },
            filter = List<Variant>::isNotEmpty,
            sorter = { sorter.sort(configuration, it) }
        )
    }
}

package com.neotive.chordia.common.cache

import com.neotive.chordia.cache.api.model.CachePolicy
import com.neotive.chordia.cache.api.tool.CacheFormatter
import com.neotive.chordia.cache.api.tool.CacheManager
import com.neotive.chordia.cache.api.tool.CacheWrapper
import com.neotive.chordia.cache.base.tool.BaseCacheManager
import com.neotive.chordia.common.base.tool.BaseChordia
import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.configuration.tool.ConfigurationManager
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.generation.api.tool.GenerationProcessor
import com.neotive.chordia.rendering.api.tool.RenderingProcessor

class CacheChordia<Image : Any>(
    override val cacheManager: CacheManager<Configuration, Map<Variant, Image>>?,
    generation: GenerationProcessor,
    rendering: RenderingProcessor<Image>,
    configurationManager: ConfigurationManager
) : BaseChordia<Image>(
    configurationManager = configurationManager,
    generation = generation,
    rendering = rendering
), CacheWrapper<Configuration, Map<Variant, Image>> {

    constructor(
        cachePolicy: CachePolicy,
        generation: GenerationProcessor,
        rendering: RenderingProcessor<Image>,
        configurationManager: ConfigurationManager,
        cacheFormatter: CacheFormatter<Configuration> = CacheChordiaFormatter()
    ) : this(
        cacheManager = BaseCacheManager(cachePolicy, cacheFormatter),
        generation = generation,
        rendering = rendering,
        configurationManager = configurationManager
    )

    override fun chordify(configuration: Configuration): Map<Variant, Image> {
        return cached(
            key = configuration,
            factory = { super.chordify(configuration) },
            filter = Map<Variant, Image>::isNotEmpty
        )
    }
}

package com.neotive.chordia.rendering.cache

import com.neotive.chordia.cache.api.model.CachePolicy
import com.neotive.chordia.cache.api.tool.CacheFormatter
import com.neotive.chordia.cache.api.tool.CacheManager
import com.neotive.chordia.cache.api.tool.CacheWrapper
import com.neotive.chordia.cache.base.tool.BaseCacheManager
import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.configuration.tool.ConfigurationManager
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.rendering.api.tool.RenderingComposer
import com.neotive.chordia.rendering.api.tool.RenderingSorter
import com.neotive.chordia.rendering.api.tool.RenderingVisualizer
import com.neotive.chordia.rendering.base.tool.BaseRenderingComposer
import com.neotive.chordia.rendering.base.tool.BaseRenderingProcessor
import com.neotive.chordia.rendering.base.tool.BaseRenderingSorter

class CacheRenderingProcessor<Image : Any>(
    override val cacheManager: CacheManager<Variant, Image>?,
    visualizer: RenderingVisualizer<Image>,
    configurationManager: ConfigurationManager,
    composer: RenderingComposer = BaseRenderingComposer(),
    private val sorter: RenderingSorter<Image> = BaseRenderingSorter()
) : BaseRenderingProcessor<Image>(
    visualizer = visualizer,
    configurationManager = configurationManager,
    composer = composer,
    sorter = sorter
), CacheWrapper<Variant, Image> {

    constructor(
        cachePolicy: CachePolicy,
        visualizer: RenderingVisualizer<Image>,
        configurationManager: ConfigurationManager,
        composer: RenderingComposer = BaseRenderingComposer(),
        sorter: RenderingSorter<Image> = BaseRenderingSorter(),
        cacheFormatter: CacheFormatter<Variant> = CacheRenderingFormatter(),
    ) : this(
        cacheManager = BaseCacheManager(cachePolicy, cacheFormatter),
        configurationManager = configurationManager,
        visualizer = visualizer,
        composer= composer,
        sorter = sorter
    )

    override fun process(configuration: Configuration, variants: List<Variant>): Map<Variant, Image> {
        return cached(
            keys = variants,
            factory = { super.process(configuration, variants) },
            filter = Map<Variant, Image>::isNotEmpty,
            sorter = { sorter.sort(configuration, it) }
        )
    }
}

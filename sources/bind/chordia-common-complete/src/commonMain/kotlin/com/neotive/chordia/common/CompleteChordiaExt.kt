package com.neotive.chordia.common

import com.neotive.chordia.cache.api.model.CachePolicy
import com.neotive.chordia.common.api.tool.Chordia
import com.neotive.chordia.common.cache.CacheChordia
import com.neotive.chordia.configuration.tool.BaseConfigurationManager
import com.neotive.chordia.generation.api.tool.GenerationProcessor
import com.neotive.chordia.generation.invoke
import com.neotive.chordia.rendering.api.model.ChordiaImage
import com.neotive.chordia.rendering.api.tool.RenderingProcessor
import com.neotive.chordia.rendering.invoke

operator fun <Image : Any> Chordia.Companion.invoke(
    cachePolicy: CachePolicy,
    generation: GenerationProcessor,
    rendering: RenderingProcessor<Image>
): Chordia<Image> {
    return CacheChordia(
        cachePolicy = cachePolicy,
        generation = generation,
        rendering = rendering,
        configurationManager = BaseConfigurationManager()
    )
}

operator fun <Image : Any> Chordia.Companion.invoke(
    cachePolicy: CachePolicy,
    rendering: RenderingProcessor<Image>
): Chordia<Image> {
    return Chordia(
        cachePolicy = cachePolicy,
        generation = GenerationProcessor(),
        rendering = rendering
    )
}

operator fun Chordia.Companion.invoke(cachePolicy: CachePolicy): Chordia<ChordiaImage> {
    return Chordia(
        cachePolicy = cachePolicy,
        rendering = RenderingProcessor()
    )
}

operator fun Chordia.Companion.invoke(
    generationCachePolicy: CachePolicy,
    renderingCachePolicy: CachePolicy,
): Chordia<ChordiaImage> {
    return Chordia(
        generation = GenerationProcessor(generationCachePolicy),
        rendering = RenderingProcessor(renderingCachePolicy),
    )
}

operator fun Chordia.Companion.invoke(
    cachePolicy: CachePolicy,
    generationCachePolicy: CachePolicy,
    renderingCachePolicy: CachePolicy,
): Chordia<ChordiaImage> {
    return Chordia(
        cachePolicy = cachePolicy,
        generation = GenerationProcessor(generationCachePolicy),
        rendering = RenderingProcessor(renderingCachePolicy),
    )
}

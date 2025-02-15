package com.neotive.chordia.generation

import com.neotive.chordia.cache.api.model.CachePolicy
import com.neotive.chordia.configuration.tool.BaseConfigurationManager
import com.neotive.chordia.generation.api.tool.GenerationProcessor
import com.neotive.chordia.generation.cache.CacheGenerationProcessor

operator fun GenerationProcessor.Companion.invoke(cachePolicy: CachePolicy): GenerationProcessor {
    return CacheGenerationProcessor(
        cachePolicy = cachePolicy,
        configurationManager = BaseConfigurationManager()
    )
}

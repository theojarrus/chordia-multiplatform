package com.neotive.chordia.generation.cache

import com.neotive.chordia.cache.api.tool.CacheFormatter
import com.neotive.chordia.configuration.model.Configuration

class CacheGenerationFormatter : CacheFormatter<Configuration> {

    override fun invoke(key: Configuration): String = with(key.parameters) {
        return "i${instrument}p${pattern}t${tonic}"
    }
}

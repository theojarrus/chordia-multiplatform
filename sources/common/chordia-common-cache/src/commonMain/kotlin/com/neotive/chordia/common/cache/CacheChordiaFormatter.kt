package com.neotive.chordia.common.cache

import com.neotive.chordia.cache.api.tool.CacheFormatter
import com.neotive.chordia.configuration.model.Configuration

class CacheChordiaFormatter : CacheFormatter<Configuration> {

    override fun invoke(key: Configuration): String {
        return key.toString()
    }
}

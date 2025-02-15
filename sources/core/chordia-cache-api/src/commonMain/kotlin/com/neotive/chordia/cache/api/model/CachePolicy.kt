package com.neotive.chordia.cache.api.model

import com.neotive.chordia.cache.api.tool.StorageEngine

sealed class CachePolicy {

    data object Disabled : CachePolicy()
    data object Session : CachePolicy()

    class Storage(val storageEngine: StorageEngine) : CachePolicy()
}

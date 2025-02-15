package com.neotive.chordia.cache.base.tool

import com.neotive.chordia.cache.api.model.CachePolicy
import com.neotive.chordia.cache.api.model.CachePolicy.Disabled
import com.neotive.chordia.cache.api.model.CachePolicy.Session
import com.neotive.chordia.cache.api.model.CachePolicy.Storage
import com.neotive.chordia.cache.api.tool.CacheFormatter
import com.neotive.chordia.cache.api.tool.CacheManager
import com.neotive.chordia.cache.api.tool.StorageEngine

class BaseCacheManager<Key : Any, Value : Any>(
    override val storageEngine: StorageEngine? = null,
    override val formatter: CacheFormatter<Key>? = null
) : BaseStorageManager<Key, Value>(), CacheManager<Key, Value> {

    private val sessionStorage: MutableMap<Key, Value> = mutableMapOf()

    override fun get(key: Key): Value? {
        return super.get(key)
            ?: sessionStorage[key]
    }

    override fun get(keys: List<Key>): Map<Key, Value> {
        return super.get(keys)
            ?: run { keys.mapNotNull { key -> sessionStorage[key]?.let { value -> key to value } }.toMap() }
    }

    override fun save(key: Key, value: Value) {
        super.save(key, value)
            ?: run { sessionStorage[key] = value }
    }

    override fun save(values: Map<Key, Value>) {
        super.save(values)
            ?: sessionStorage.putAll(values)
    }

    override fun invalidate() {
        super.invalidate()
        sessionStorage.clear()
    }

    companion object {

        operator fun <Key : Any, Value : Any> invoke(
            cachePolicy: CachePolicy,
            formatter: CacheFormatter<Key>
        ): BaseCacheManager<Key, Value>? {
            return when (cachePolicy) {
                Disabled -> null
                Session -> BaseCacheManager()
                is Storage -> BaseCacheManager(
                    storageEngine = cachePolicy.storageEngine,
                    formatter = formatter
                )
            }
        }
    }
}

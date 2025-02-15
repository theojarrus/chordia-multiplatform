package com.neotive.chordia.cache.base.tool

import com.neotive.chordia.cache.api.tool.StorageManager
import com.neotive.chordia.cache.api.tool.StorageEngine

abstract class BaseStorageManager<K : Any, V : Any> : StorageManager<K, V> {

    protected abstract val storageEngine: StorageEngine?

    override fun get(key: K): V? {
        return storageEngine
            ?.get(key) {
                formatter?.invoke(it)
                    ?: it.toString()
            }
    }

    override fun get(keys: List<K>): Map<K, V>? {
        return storageEngine
            ?.get<K, V>(keys) {
                formatter?.invoke(it)
                    ?: it.toString()
            }
    }

    override fun save(key: K, value: V): Unit? {
        return storageEngine
            ?.save(key, value) {
                formatter?.invoke(it)
                    ?: it.toString()
            }
    }

    override fun save(values: Map<K, V>): Unit? {
        return storageEngine
            ?.save(values) {
                formatter?.invoke(it)
                    ?: it.toString()
            }
    }

    override fun invalidate(): Unit? {
        return storageEngine
            ?.invalidate()
    }
}

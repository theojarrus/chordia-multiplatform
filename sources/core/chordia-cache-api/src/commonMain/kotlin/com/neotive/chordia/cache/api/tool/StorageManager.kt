package com.neotive.chordia.cache.api.tool

interface StorageManager<K : Any, V : Any> {

    val formatter: CacheFormatter<K>? get() = null

    fun get(key: K): V?

    fun get(keys: List<K>): Map<K, V>?

    fun save(key: K, value: V): Unit?

    fun save(values: Map<K, V>): Unit?

    fun invalidate(): Unit?
}

package com.neotive.chordia.cache.api.tool

interface StorageEngine {

    fun <K : Any, V : Any> get(key: K, formatter: (K) -> String): V?

    fun <K : Any, V : Any> get(keys: List<K>, formatter: (K) -> String): Map<K, V>

    fun <K : Any, V : Any> save(key: K, value: V, formatter: (K) -> String)

    fun <K : Any, V : Any> save(values: Map<K, V>, formatter: (K) -> String)

    fun invalidate(): Unit?
}

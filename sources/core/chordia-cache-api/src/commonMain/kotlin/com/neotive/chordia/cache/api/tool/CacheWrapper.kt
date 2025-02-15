package com.neotive.chordia.cache.api.tool

interface CacheWrapper<K : Any, V : Any> {

    val cacheManager: CacheManager<K, V>?

    fun cached(
        key: K,
        factory: () -> V,
        filter: (V) -> Boolean = { true },
        sorter: (V) -> V = { it }
    ): V {
        return cacheManager
            ?.get(key)
            ?.takeIf(filter)
            ?.let(sorter)
            ?: factory()
                .also { value ->
                    value
                        .takeIf(filter)
                        ?.let { cacheManager?.save(key, it) }
                }
    }

    fun cached(
        keys: List<K>,
        factory: () -> Map<K, V>,
        filter: (Map<K, V>) -> Boolean = { true },
        sorter: (Map<K, V>) -> Map<K, V> = { it }
    ): Map<K, V> {
        return cacheManager
            ?.get(keys)
            ?.takeIf(filter)
            ?.let(sorter)
            ?: factory()
                .also { value ->
                    value
                        .takeIf(filter)
                        ?.let { cacheManager?.save(it) }
                }
    }
}

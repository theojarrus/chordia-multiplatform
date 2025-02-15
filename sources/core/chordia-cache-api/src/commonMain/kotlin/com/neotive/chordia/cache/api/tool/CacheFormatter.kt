package com.neotive.chordia.cache.api.tool

interface CacheFormatter<Key : Any> {

    operator fun invoke(key: Key): String
}

package com.neotive.chordia.rendering.cache

import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.cache.api.tool.CacheFormatter

class CacheRenderingFormatter : CacheFormatter<Variant> {

    override fun invoke(key: Variant): String {
        return key.points
            .joinToString(separator = "s") { point -> "l${point.line}p${point.position}" }
            .plus(EXTENSION)
    }

    companion object {

        private const val EXTENSION = ".svg"
    }
}

package com.neotive.chordia.configuration.model

import com.neotive.chordia.configuration.tool.ConfigurationManager
import kotlin.reflect.KProperty0

data class Configuration(
    val parameters: Parameters,
    val configurationManager: ConfigurationManager
) {

    inline fun <reified Value : Any?> get(property: KProperty0<Value>): Value {
        return configurationManager.get(parameters.instrument.type::class, property)
            .runCatching { this as Value }
            .getOrElse { property.get() }
    }
}

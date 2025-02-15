package com.neotive.chordia.configuration.tool

import com.neotive.chordia.core.model.Type
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty0

class BaseConfigurationManager(
    private val storage: MutableMap<KClass<out Type>, MutableMap<String, Any?>> = mutableMapOf()
) : ConfigurationManager {

    override fun clone(): ConfigurationManager {
        return BaseConfigurationManager(storage)
    }

    override fun <Key : Type, Value : Any?> set(
        type: KClass<Key>,
        vararg properties: Pair<KProperty<Value>, Value>
    ) {
        properties
            .forEach { (property, value) ->
                storage
                    .getOrPut(type, ::mutableMapOf)
                    .apply {
                        remove(property.name)
                        put(property.name, value)
                    }
            }
    }

    override fun <Key : Type, Value : Any?> get(type: KClass<Key>, property: KProperty0<Value>): Any? {
        return when {
            storage[type]?.contains(property.name) == true -> storage[type]?.get(property.name)
            storage[Type::class]?.contains(property.name) == true -> storage[Type::class]?.get(property.name)
            else -> property.get()
        }
    }

    override fun <Key : Type> clear(type: KClass<Key>?) {
        when (type) {
            null -> storage.clear()
            else -> storage[type]?.clear()
        }
    }
}

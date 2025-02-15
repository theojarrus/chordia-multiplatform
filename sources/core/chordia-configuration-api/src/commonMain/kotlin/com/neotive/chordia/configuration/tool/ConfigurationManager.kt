package com.neotive.chordia.configuration.tool

import com.neotive.chordia.core.model.Type
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty0

interface ConfigurationManager {

    fun <Value : Any?> set(vararg properties: Pair<KProperty<Value>, Value>) = set(Type::class, *properties)

    fun <Key : Type, Value : Any?> set(type: KClass<Key>, vararg properties: Pair<KProperty<Value>, Value>)

    fun <Key : Type, Value : Any?> get(type: KClass<Key>, property: KProperty0<Value>): Any?

    fun <Key : Type> clear(type: KClass<Key>? = null)

    fun clone(): ConfigurationManager
}

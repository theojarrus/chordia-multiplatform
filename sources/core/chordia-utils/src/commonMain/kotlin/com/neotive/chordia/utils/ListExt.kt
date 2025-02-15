package com.neotive.chordia.utils

import kotlin.reflect.KProperty1

inline fun <reified T> List<T>.scale(size: Int): List<T> {
    return List(size) { get(it % this.size) }
}

inline fun <reified T, reified V : Collection<*>> List<T>.ifEmptyOrPlusWithDistinctBy(
    property: KProperty1<T, V>,
    predicate: () -> Boolean,
    block: List<T>.() -> List<T>
): List<T> {
    return if (predicate()) {
        plus(
            flatMap(property::get)
                .toSet()
                .let { collection ->
                    block(this)
                        .filterNot { collection.containsAll(property.get(it)) }
                }
        )
    } else {
        ifEmpty { block(this) }
    }
}

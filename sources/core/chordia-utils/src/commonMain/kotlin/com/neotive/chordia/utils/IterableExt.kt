package com.neotive.chordia.utils

inline fun <reified T> Iterable<T>.compareAny(predicate: (T, T) -> Boolean): Boolean {
    forEachIndexed { index, a ->
        elementAtOrNull(index.inc())
            ?.let { b ->
                if (predicate(a, b)) {
                    return true
                }
            }
    }
    return false
}

inline fun <reified T> Iterable<T>.compareAll(predicate: (T, T) -> Boolean): Boolean {
    return !compareAny { a, b -> !predicate(a, b) }
}

inline fun <reified T : Comparable<T>> Iterable<T>.isSorted(): Boolean {
    return compareAll { a, b -> a < b }
}

inline fun <reified T : Comparable<T>> Iterable<T>.isSortedByDescending(): Boolean {
    return compareAll { a, b -> a > b }
}

inline fun <T> Iterable<T>.filterByCompare(predicate: (T, T) -> Boolean): List<T> {
    return filterIndexed { index, a ->
        elementAtOrNull(index.inc())
            ?.let { b -> predicate(a, b) }
            ?: true
    }
}

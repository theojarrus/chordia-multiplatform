package com.neotive.chordia.utils

import kotlin.ranges.IntRange.Companion.EMPTY

operator fun Int.plus(other: Int?): Int {
    return this + (other ?: 0)
}

operator fun Int?.minus(other: Int?): Int {
    return (this ?: 0) - (other ?: 0)
}

operator fun Int?.rangeTo(other: Int?): IntRange {
    return if (this != null && other != null) rangeTo(other) else EMPTY
}

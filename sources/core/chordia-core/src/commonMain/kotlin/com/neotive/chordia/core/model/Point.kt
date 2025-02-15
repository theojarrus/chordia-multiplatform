package com.neotive.chordia.core.model

data class Point(
    val line: Int,
    val note: Note,
    val position: Int,
    val hint: Int? = null
)

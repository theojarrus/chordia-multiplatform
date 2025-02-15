package com.neotive.chordia.generation.configuration

interface BaseGenerationIteratorConfiguration {

    val minLine: Int
    val maxLine: Int?

    object Default : BaseGenerationIteratorConfiguration {

        override val minLine: Int = 0
        override val maxLine: Int? = null
    }
}

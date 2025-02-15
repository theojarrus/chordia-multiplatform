package com.neotive.chordia.generation.api.tool

import com.neotive.chordia.configuration.model.Configuration

interface GenerationIterator {

    fun start(configuration: Configuration): Int

    fun next(configuration: Configuration, line: Int): Int
}

package com.neotive.chordia.generation.base.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.generation.api.tool.GenerationIterator
import com.neotive.chordia.generation.configuration.IteratorBinding

class BaseGenerationIterator : GenerationIterator {

    override fun start(configuration: Configuration): Int {
        return IteratorBinding(configuration).start()
    }

    override fun next(configuration: Configuration, line: Int): Int {
        return IteratorBinding(configuration).next(line)
    }

    private fun IteratorBinding.start(): Int {
        return minLine
    }

    private fun IteratorBinding.next(line: Int): Int {
        return line.inc()
            .takeIf { it != maxLine }
            ?: END
    }

    companion object {

        private const val END = -1
    }
}

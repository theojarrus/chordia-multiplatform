package com.neotive.chordia.generation.configuration

import com.neotive.chordia.configuration.model.Configuration

internal typealias IteratorBinding = BaseGenerationIteratorBinding

internal class BaseGenerationIteratorBinding(val configuration: Configuration) : BaseGenerationIteratorConfiguration {

    override val minLine: Int get() = configuration.get(BaseGenerationIteratorConfiguration.Default::minLine)
    override val maxLine: Int? get() = configuration.get(BaseGenerationIteratorConfiguration.Default::maxLine)
}

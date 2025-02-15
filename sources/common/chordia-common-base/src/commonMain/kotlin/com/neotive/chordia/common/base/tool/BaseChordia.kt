package com.neotive.chordia.common.base.tool

import com.neotive.chordia.common.api.tool.Chordia
import com.neotive.chordia.common.configuration.ChordiaBinding
import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.configuration.tool.ConfigurationManager
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.generation.api.tool.GenerationProcessor
import com.neotive.chordia.rendering.api.tool.RenderingProcessor

open class BaseChordia<Image>(
    private val generation: GenerationProcessor,
    private val rendering: RenderingProcessor<Image>,
    override val configurationManager: ConfigurationManager
) : Chordia<Image> {

    override fun chordify(configuration: Configuration): Map<Variant, Image> {
        return ChordiaBinding(configuration).chordify()
    }

    private fun ChordiaBinding.chordify(): Map<Variant, Image> {
        return generation.process(configuration)
            .let { rendering.process(configuration, it) }
    }
}

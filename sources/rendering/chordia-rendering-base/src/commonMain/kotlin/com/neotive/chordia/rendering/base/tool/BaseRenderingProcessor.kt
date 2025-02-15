package com.neotive.chordia.rendering.base.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.configuration.tool.ConfigurationManager
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.rendering.api.tool.RenderingComposer
import com.neotive.chordia.rendering.api.tool.RenderingProcessor
import com.neotive.chordia.rendering.api.tool.RenderingSorter
import com.neotive.chordia.rendering.api.tool.RenderingVisualizer
import com.neotive.chordia.rendering.configuration.ProcessorBinding

open class BaseRenderingProcessor<Image>(
    private val visualizer: RenderingVisualizer<Image>,
    override val configurationManager: ConfigurationManager,
    private val composer: RenderingComposer = BaseRenderingComposer(),
    private val sorter: RenderingSorter<Image> = BaseRenderingSorter()
) : RenderingProcessor<Image> {

    override fun process(configuration: Configuration, variants: List<Variant>): Map<Variant, Image> {
        return ProcessorBinding(configuration).process(variants)
    }

    private fun ProcessorBinding.process(variants: List<Variant>): Map<Variant, Image> {
        return variants
            .associateWith { composer.compose(configuration, it) }
            .mapValues { (_, composition) -> visualizer.visualize(configuration, composition) }
            .let { sorter.sort(configuration, it) }
    }
}

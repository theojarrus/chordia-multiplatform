package com.neotive.chordia.common

import com.neotive.chordia.common.api.tool.Chordia
import com.neotive.chordia.common.base.tool.BaseChordia
import com.neotive.chordia.configuration.tool.BaseConfigurationManager
import com.neotive.chordia.generation.api.tool.GenerationProcessor
import com.neotive.chordia.generation.invoke
import com.neotive.chordia.rendering.api.model.ChordiaImage
import com.neotive.chordia.rendering.api.tool.RenderingProcessor
import com.neotive.chordia.rendering.invoke

operator fun <Image> Chordia.Companion.invoke(
    generation: GenerationProcessor,
    rendering: RenderingProcessor<Image>
): Chordia<Image> {
    return BaseChordia(
        generation = generation,
        rendering = rendering,
        configurationManager = BaseConfigurationManager()
    )
}

operator fun <Image> Chordia.Companion.invoke(rendering: RenderingProcessor<Image>): Chordia<Image> {
    return Chordia(
        generation = GenerationProcessor(),
        rendering = rendering
    )
}

operator fun Chordia.Companion.invoke(): Chordia<ChordiaImage> {
    return Chordia(rendering = RenderingProcessor())
}

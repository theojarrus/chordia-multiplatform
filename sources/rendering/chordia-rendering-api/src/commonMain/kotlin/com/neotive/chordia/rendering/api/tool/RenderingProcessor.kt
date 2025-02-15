package com.neotive.chordia.rendering.api.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.configuration.model.Parameters
import com.neotive.chordia.configuration.tool.ConfigurationHolder
import com.neotive.chordia.configuration.tool.ConfigurationManager
import com.neotive.chordia.core.model.Instrument
import com.neotive.chordia.core.model.Note.C
import com.neotive.chordia.core.model.Pattern.Major
import com.neotive.chordia.core.model.Variant

interface RenderingProcessor<Image> : ConfigurationHolder {

    fun process(
        parameters: Parameters,
        variants: List<Variant>,
        configurationManager: ConfigurationManager = this.configurationManager
    ): Map<Variant, Image> = process(
        configuration = Configuration(parameters, configurationManager),
        variants = variants
    )

    fun process(
        configuration: Configuration,
        variants: List<Variant>
    ): Map<Variant, Image>

    companion object
}

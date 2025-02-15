package com.neotive.chordia.generation.api.tool

import com.neotive.chordia.configuration.tool.ConfigurationHolder
import com.neotive.chordia.configuration.tool.ConfigurationManager
import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.configuration.model.Parameters
import com.neotive.chordia.core.model.Variant

interface GenerationProcessor : ConfigurationHolder {

    fun process(
        parameters: Parameters,
        configurationManager: ConfigurationManager = this.configurationManager
    ): List<Variant> = process(
        configuration = Configuration(parameters, configurationManager)
    )

    fun process(configuration: Configuration): List<Variant>

    companion object
}

package com.neotive.chordia.common.api.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.configuration.model.Parameters
import com.neotive.chordia.configuration.tool.ConfigurationHolder
import com.neotive.chordia.configuration.tool.ConfigurationManager
import com.neotive.chordia.core.model.Variant

interface Chordia<Image> : ConfigurationHolder {

    fun chordify(
        parameters: Parameters,
        configurationManager: ConfigurationManager = this.configurationManager
    ): Map<Variant, Image> = chordify(
        configuration = Configuration(parameters, configurationManager)
    )

    fun chordify(configuration: Configuration): Map<Variant, Image>

    companion object
}

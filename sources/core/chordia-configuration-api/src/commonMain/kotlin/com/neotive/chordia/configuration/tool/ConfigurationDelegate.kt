package com.neotive.chordia.configuration.tool

import com.neotive.chordia.configuration.model.Configuration

interface ConfigurationDelegate {

    fun isResponsible(configuration: Configuration): Boolean
}

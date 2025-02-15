package com.neotive.chordia.configuration.tool

import com.neotive.chordia.configuration.model.Configuration

interface ConfigurationDelegator<Delegate : ConfigurationDelegate> {

    val delegates: List<Delegate>

    fun getDelegateOrNull(configuration: Configuration): Delegate? {
        return delegates.firstOrNull { it.isResponsible(configuration) }
    }

    fun getDelegate(configuration: Configuration): Delegate {
        return requireNotNull(getDelegateOrNull(configuration)) { "${this::class}: No delegate for $configuration" }
    }
}

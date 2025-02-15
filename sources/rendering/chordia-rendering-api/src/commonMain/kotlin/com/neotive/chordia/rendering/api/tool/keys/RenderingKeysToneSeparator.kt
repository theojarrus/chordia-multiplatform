package com.neotive.chordia.rendering.api.tool.keys

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.rendering.api.model.keys.Tones

interface RenderingKeysToneSeparator {

    fun separate(configuration: Configuration, variant: Variant): Tones
}

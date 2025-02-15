package com.neotive.chordia.rendering.api.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Variant

interface RenderingSorter<Image> {

    fun sort(configuration: Configuration, renders: Map<Variant, Image>): Map<Variant, Image>
}

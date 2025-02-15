package com.neotive.chordia.rendering.base.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.rendering.api.tool.RenderingSorter
import com.neotive.chordia.rendering.configuration.SorterBinding

class BaseRenderingSorter<Image> : RenderingSorter<Image> {

    override fun sort(configuration: Configuration, renders: Map<Variant, Image>): Map<Variant, Image> {
        return SorterBinding(configuration).sort(renders)
    }

    private fun SorterBinding.sort(renders: Map<Variant, Image>): Map<Variant, Image> {
        return renders
    }
}

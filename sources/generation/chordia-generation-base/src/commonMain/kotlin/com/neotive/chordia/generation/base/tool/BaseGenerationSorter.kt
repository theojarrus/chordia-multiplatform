package com.neotive.chordia.generation.base.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.generation.api.tool.GenerationSorter
import com.neotive.chordia.generation.configuration.SorterBinding

class BaseGenerationSorter : GenerationSorter {

    override fun sort(configuration: Configuration, variants: List<Variant>): List<Variant> {
        return SorterBinding(configuration).sort(variants)
    }

    private fun SorterBinding.sort(variants: List<Variant>): List<Variant> {
        return variants.sortedBy { it.points.firstOrNull()?.position }
    }
}

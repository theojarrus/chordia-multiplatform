package com.neotive.chordia.generation.api.tool.strings

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Row
import com.neotive.chordia.core.model.Variant

interface GenerationStringsRowsFinder {

    fun getRows(configuration: Configuration, variant: Variant): List<Row>
}

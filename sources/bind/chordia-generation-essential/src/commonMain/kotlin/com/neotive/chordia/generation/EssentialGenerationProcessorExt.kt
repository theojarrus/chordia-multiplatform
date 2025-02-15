package com.neotive.chordia.generation

import com.neotive.chordia.configuration.tool.BaseConfigurationManager
import com.neotive.chordia.generation.api.tool.GenerationProcessor
import com.neotive.chordia.generation.base.tool.BaseGenerationProcessor

operator fun GenerationProcessor.Companion.invoke(): GenerationProcessor {
    return BaseGenerationProcessor(configurationManager = BaseConfigurationManager())
}

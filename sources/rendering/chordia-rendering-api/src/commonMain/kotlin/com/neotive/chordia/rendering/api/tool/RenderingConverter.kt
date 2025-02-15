package com.neotive.chordia.rendering.api.tool

import com.neotive.chordia.configuration.model.Configuration

interface RenderingConverter<Raw : Any, Image> {

    fun convert(configuration: Configuration, raw: Raw): Image
}

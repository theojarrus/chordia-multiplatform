package com.neotive.chordia.svg.configuration

import com.neotive.chordia.configuration.model.Configuration

internal typealias ConverterBinding = BaseRenderingSvgConverterBinding

internal class BaseRenderingSvgConverterBinding(
    val configuration: Configuration
) : BaseRenderingSvgConverterConfiguration

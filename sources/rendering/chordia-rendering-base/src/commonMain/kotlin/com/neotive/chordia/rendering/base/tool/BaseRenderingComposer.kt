package com.neotive.chordia.rendering.base.tool

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.configuration.tool.ConfigurationDelegator
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.rendering.api.model.shape.Shape.Composition
import com.neotive.chordia.rendering.api.tool.RenderingComposer
import com.neotive.chordia.rendering.api.tool.RenderingComposerDelegate
import com.neotive.chordia.rendering.base.tool.keys.BaseRenderingKeysComposer
import com.neotive.chordia.rendering.base.tool.strings.BaseRenderingStringsComposer

class BaseRenderingComposer(
    override val delegates: List<RenderingComposerDelegate> = listOf(
        BaseRenderingKeysComposer(),
        BaseRenderingStringsComposer()
    )
) : RenderingComposer, ConfigurationDelegator<RenderingComposerDelegate> {

    override fun compose(configuration: Configuration, variant: Variant): Composition {
        return getDelegate(configuration).compose(configuration, variant)
    }
}

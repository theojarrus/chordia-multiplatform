package com.neotive.chordia.rendering.configuration.keys

import com.neotive.chordia.rendering.configuration.BaseRenderingComposerConfiguration
import com.neotive.chordia.rendering.api.model.shape.Font
import com.neotive.chordia.rendering.api.model.shape.Theme

interface BaseRenderingKeysComposerConfiguration : BaseRenderingComposerConfiguration {

    val halftoneWidth: Float
    val halftoneHeight: Float

    object Default : BaseRenderingKeysComposerConfiguration {
        override val theme: Theme = Theme()
        override val font: Font = Font()
        override val scale: Float = 10f
        override val padding: Float = 0.5f
        override val rows: Int = 5
        override val weight: Float = 0.1f
        override val radius: Float = 0.2f
        override val noteSize: Float = 0.3f
        override val textSize: Float = noteSize * 1.4f
        override val halftoneWidth: Float = 0.8f
        override val halftoneHeight: Float = 0.7f
    }
}

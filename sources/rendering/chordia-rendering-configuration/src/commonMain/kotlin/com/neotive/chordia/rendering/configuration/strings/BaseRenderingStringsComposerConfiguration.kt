package com.neotive.chordia.rendering.configuration.strings

import com.neotive.chordia.rendering.configuration.BaseRenderingComposerConfiguration
import com.neotive.chordia.rendering.api.model.shape.Font
import com.neotive.chordia.rendering.api.model.shape.Theme

interface BaseRenderingStringsComposerConfiguration : BaseRenderingComposerConfiguration {

    val floatingSize: Float
    val floatingPadding: Float

    object Default : BaseRenderingStringsComposerConfiguration {

        override val theme: Theme = Theme()
        override val font: Font = Font()
        override val scale: Float = 10f
        override val padding: Float = 1f
        override val rows: Int = 5
        override val weight: Float = 0.1f
        override val radius: Float = 0.2f
        override val noteSize: Float = 0.35f
        override val textSize: Float = noteSize * 1.25f
        override val floatingSize: Float = 0.3f
        override val floatingPadding: Float = 0.1f
    }
}

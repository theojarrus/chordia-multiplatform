package com.neotive.chordia.rendering.configuration

import com.neotive.chordia.rendering.api.model.shape.Font
import com.neotive.chordia.rendering.api.model.shape.Theme

interface BaseRenderingComposerConfiguration {

    val theme: Theme
    val font: Font
    val scale: Float
    val padding: Float
    val rows: Int
    val weight: Float
    val radius: Float
    val noteSize: Float
    val textSize: Float
}

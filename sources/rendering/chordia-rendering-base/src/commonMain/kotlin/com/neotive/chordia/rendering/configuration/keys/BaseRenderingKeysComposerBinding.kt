package com.neotive.chordia.rendering.configuration.keys

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.rendering.api.model.shape.Font
import com.neotive.chordia.rendering.api.model.shape.Theme

internal typealias KeysComposerBinding = BaseRenderingKeysComposerBinding

internal class BaseRenderingKeysComposerBinding(
    val configuration: Configuration
) : BaseRenderingKeysComposerConfiguration {

    override val theme: Theme get() = configuration.get(BaseRenderingKeysComposerConfiguration.Default::theme)
    override val font: Font get() = configuration.get(BaseRenderingKeysComposerConfiguration.Default::font)
    override val scale: Float get() = maxOf(SCALE_MIN, configuration.get(BaseRenderingKeysComposerConfiguration.Default::scale))
    override val padding: Float get() = maxOf(PADDING_MIN, configuration.get(BaseRenderingKeysComposerConfiguration.Default::padding))
    override val rows: Int get() = maxOf(ROWS_MIN, configuration.get(BaseRenderingKeysComposerConfiguration.Default::rows))
    override val weight: Float get() = maxOf(WEIGHT_MIN, minOf(WEIGHT_MAX, configuration.get(BaseRenderingKeysComposerConfiguration.Default::weight)))
    override val radius: Float get() = minOf(RADIUS_MAX, configuration.get(BaseRenderingKeysComposerConfiguration.Default::radius))
    override val noteSize: Float get() = maxOf(NOTE_SIZE_MIN, minOf(halftoneWidth / 2, configuration.get(BaseRenderingKeysComposerConfiguration.Default::noteSize)))
    override val textSize: Float get() = minOf(noteSize * 2, configuration.get(BaseRenderingKeysComposerConfiguration.Default::textSize))
    override val halftoneWidth: Float get() = maxOf(HALFTONE_WIDTH_MIN, minOf(HALFTONE_WIDTH_MAX, configuration.get(BaseRenderingKeysComposerConfiguration.Default::halftoneWidth)))
    override val halftoneHeight: Float get() = rows * maxOf(HALFTONE_HEIGHT_MIN, minOf(HALFTONE_HEIGHT_MAX, configuration.get(BaseRenderingKeysComposerConfiguration.Default::halftoneHeight)))

    val halftonePadding: Float get() = (1f - halftoneWidth) / 2
    val sidePadding: Float get() = padding * 2

    companion object {

        private const val SCALE_MIN = 10f
        private const val PADDING_MIN = 0.05f
        private const val ROWS_MIN = 5
        private const val WEIGHT_MIN = 0.1f
        private const val WEIGHT_MAX = 0.2f
        private const val RADIUS_MAX = 0.5f
        private const val NOTE_SIZE_MIN = 0.2f
        private const val HALFTONE_WIDTH_MIN = 0.6f
        private const val HALFTONE_WIDTH_MAX = 0.9f
        private const val HALFTONE_HEIGHT_MIN = 0.2f
        private const val HALFTONE_HEIGHT_MAX = 0.8f
    }
}

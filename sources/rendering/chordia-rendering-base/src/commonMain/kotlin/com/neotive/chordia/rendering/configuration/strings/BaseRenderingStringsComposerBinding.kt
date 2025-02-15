package com.neotive.chordia.rendering.configuration.strings

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.rendering.api.model.shape.Font
import com.neotive.chordia.rendering.api.model.shape.Theme

internal typealias StringsComposerBinding = BaseRenderingStringsComposerBinding

internal class BaseRenderingStringsComposerBinding(
    val configuration: Configuration
) : BaseRenderingStringsComposerConfiguration {

    override val theme: Theme get() = configuration.get(BaseRenderingStringsComposerConfiguration.Default::theme)
    override val font: Font get() = configuration.get(BaseRenderingStringsComposerConfiguration.Default::font)
    override val scale: Float get() = maxOf(SCALE_MIN, configuration.get(BaseRenderingStringsComposerConfiguration.Default::scale))
    override val padding: Float get() = maxOf(PADDING_MIN, configuration.get(BaseRenderingStringsComposerConfiguration.Default::padding))
    override val rows: Int get() = maxOf(ROWS_MIN, configuration.get(BaseRenderingStringsComposerConfiguration.Default::rows))
    override val weight: Float get() = maxOf(WEIGHT_MIN, minOf(WEIGHT_MAX, configuration.get(BaseRenderingStringsComposerConfiguration.Default::weight)))
    override val radius: Float get() = minOf(RADIUS_MAX, configuration.get(BaseRenderingStringsComposerConfiguration.Default::radius))
    override val noteSize: Float get() = maxOf(NOTE_SIZE_MIN, minOf(NOTE_SIZE_MAX, configuration.get(BaseRenderingStringsComposerConfiguration.Default::noteSize)))
    override val textSize: Float get() = minOf(noteSize * 2, configuration.get(BaseRenderingStringsComposerConfiguration.Default::textSize))
    override val floatingSize: Float get() = maxOf(FLOATING_SIZE_MIN, minOf(FLOATING_SIZE_MAX, configuration.get(BaseRenderingStringsComposerConfiguration.Default::floatingSize)))
    override val floatingPadding: Float get() = maxOf(FLOATING_PADDING_MIN, minOf(FULL - floatingSize * 2 - FLOATING_MARGIN_MIN, configuration.get(BaseRenderingStringsComposerConfiguration.Default::floatingPadding)))

    val sidePadding: Float get() = padding * 2

    companion object {

        private const val FULL = 1f
        private const val SCALE_MIN = 10f
        private const val PADDING_MIN = 1f
        private const val ROWS_MIN = 5
        private const val WEIGHT_MIN = 0.1f
        private const val WEIGHT_MAX = 0.2f
        private const val RADIUS_MAX = 0.5f
        private const val NOTE_SIZE_MIN = 0.25f
        private const val NOTE_SIZE_MAX = 0.4f
        private const val FLOATING_MARGIN_MIN = 0.2f
        private const val FLOATING_PADDING_MIN = 0.1f
        private const val FLOATING_SIZE_MIN = 0.1f
        private const val FLOATING_SIZE_MAX = 0.4f
    }
}

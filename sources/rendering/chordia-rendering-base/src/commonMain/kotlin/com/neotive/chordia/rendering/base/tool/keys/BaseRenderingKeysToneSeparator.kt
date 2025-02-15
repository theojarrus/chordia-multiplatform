package com.neotive.chordia.rendering.base.tool.keys

import com.neotive.chordia.configuration.model.Configuration
import com.neotive.chordia.core.model.Variant
import com.neotive.chordia.rendering.api.model.keys.Tone
import com.neotive.chordia.rendering.api.model.keys.Tones
import com.neotive.chordia.rendering.api.tool.keys.RenderingKeysToneSeparator
import com.neotive.chordia.rendering.configuration.keys.KeysToneSeparatorBinding

class BaseRenderingKeysToneSeparator : RenderingKeysToneSeparator {

    override fun separate(configuration: Configuration, variant: Variant): Tones {
        return KeysToneSeparatorBinding(configuration).separate(variant)
    }

    private fun KeysToneSeparatorBinding.separate(variant: Variant): Tones = with(configuration.parameters) {
        return List(instrument.length.value.inc()) { Tone(instrument.lines[it % instrument.lines.size], it) }
            .let { tones ->
                tones
                    .partition { it.note.isHalftone }
                    .let { (halftones, fulltones) -> Tones(halftones, fulltones) }
            }
    }
}

package com.neotive.chordia.rendering.api.model.keys

data class Tones(
    val halftones: List<Tone>,
    val fulltones: List<Tone>
) {

    fun shifted(position: Int): Int {
        return position - halftones.count { halftone -> halftone.position < position }
    }
}

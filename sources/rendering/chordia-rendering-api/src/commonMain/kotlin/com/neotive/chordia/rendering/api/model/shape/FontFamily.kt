package com.neotive.chordia.rendering.api.model.shape

sealed class FontFamily(val name: String) {

    data object SystemUi : FontFamily("system-ui")
    data object Serif : FontFamily("serif")
    data object SansSerif : FontFamily("sans-serif")
    data object Monospace : FontFamily("monospace")

    class Custom(name: String) : FontFamily(name)

    companion object {

        fun get(fontFamily: String): FontFamily {
            return when (fontFamily) {
                SystemUi.name -> SystemUi
                Serif.name -> Serif
                SansSerif.name -> SansSerif
                Monospace.name -> Monospace
                else -> Custom(fontFamily)
            }
        }
    }
}

package com.neotive.chordia.rendering.api.model.shape

sealed class FontWeight(val value: Int) {

    data object Thin : FontWeight(100)
    data object ExtraLight : FontWeight(200)
    data object Light : FontWeight(300)
    data object Regular : FontWeight(400)
    data object Medium : FontWeight(500)
    data object SemiBold : FontWeight(600)
    data object Bold : FontWeight(700)
    data object ExtraBold : FontWeight(800)
    data object Black : FontWeight(900)

    class Custom(value: Int) : FontWeight(value)

    companion object {

        fun get(value: Int): FontWeight {
            return when (value) {
                Thin.value -> Thin
                ExtraLight.value -> ExtraLight
                Light.value -> Light
                Regular.value -> Regular
                Medium.value -> Medium
                SemiBold.value -> SemiBold
                Bold.value -> Bold
                ExtraBold.value -> ExtraBold
                Black.value -> Black
                else -> Custom(value)
            }
        }
    }
}

package com.neotive.chordia.sample.android.ui.model

sealed interface Option {

    class MultipleOption<T>(
        val title: String,
        val values: List<T>,
        val active: () -> T?,
        val shortFormatter: (T) -> String,
        val longFormatter: (T) -> String = shortFormatter,
        val onSet: (T) -> Unit
    ) : Option

    class SingleOption<T : Number>(
        val title: String,
        val value: T,
        val onSet: (T) -> Unit
    )
}

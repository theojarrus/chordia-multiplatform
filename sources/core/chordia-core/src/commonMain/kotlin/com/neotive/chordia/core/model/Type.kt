package com.neotive.chordia.core.model

sealed interface Type {

    data object Keys : Type
    data object Strings : Type
}

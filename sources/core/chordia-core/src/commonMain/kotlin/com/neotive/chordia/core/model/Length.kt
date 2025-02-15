package com.neotive.chordia.core.model

sealed class Length(val value: Int) {

    sealed class Guitar(value: Int) : Length(value) {

        data object Standard : Guitar(22)
    }

    sealed class Bass(value: Int) : Length(value) {

        data object Standard : Bass(22)
    }

    sealed class Ukulele(value: Int) : Length(value) {

        data object Standard : Ukulele(12)
    }

    sealed class Piano(value: Int) : Length(value) {

        data object Standard : Piano(24)
    }

    sealed class Kalimba(value: Int) : Length(value) {

        data object Twelve : Piano(12)
    }

    class Custom(value: Int) : Length(value)
}

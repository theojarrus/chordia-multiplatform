package com.neotive.chordia.core.model

import com.neotive.chordia.core.model.Note.*

sealed class Drop(vararg val lines: Note) {

    sealed class Piano(vararg lines: Note) : Drop(*lines) {

        data object Standard : Piano(C, Cs, D, Ds, E, F, Fs, G, Gs, A, Bb, B)
    }

    sealed class Kalimba(vararg lines: Note) : Drop(*lines) {

        data object GTwelve : Guitar(C, A, Fs, D, B, G, A, C, E, G, B, D)
    }

    sealed class Guitar(vararg lines: Note) : Drop(*lines) {

        data object Standard : Guitar(E, A, D, G, B, E)
    }

    sealed class Bass(vararg lines: Note) : Drop(*lines) {

        data object Standard : Guitar(E, A, D, G)
    }

    sealed class Ukulele(vararg lines: Note) : Drop(*lines) {

        data object Standard : Guitar(G, C, E, A)
    }

    class Custom(vararg lines: Note) : Drop(*lines)
}

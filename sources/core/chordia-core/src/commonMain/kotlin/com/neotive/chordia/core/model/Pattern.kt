package com.neotive.chordia.core.model

import com.neotive.chordia.core.model.Note.*

sealed class Pattern(vararg val notes: Note) {

    data object Major : Pattern(C, E, G)
    data object Maj6 : Pattern(C, E, G, A)
    data object Maj69 : Pattern(C, E, G, A, D)
    data object Maj7 : Pattern(C, E, G, B)
    data object Maj9 : Pattern(C, E, G, B, D)
    data object Maj11 : Pattern(C, E, G, B, D, F)
    data object Maj13 : Pattern(C, E, G, B, D, F, A)

    data object Minor : Pattern(C, Ds, G)
    data object Min6 : Pattern(C, Ds, G, A)
    data object Min7 : Pattern(C, Ds, G, Bb)
    data object Min9 : Pattern(C, Ds, G, Bb, D)
    data object Min11 : Pattern(C, Ds, G, Bb, D, F)
    data object Min13 : Pattern(C, Ds, G, Bb, D, F, A)
    data object MMaj : Pattern(C, Ds, G, B)

    data object Dom7 : Pattern(C, E, G, Bb)
    data object Dom9 : Pattern(C, E, G, Bb, D)
    data object Dom11 : Pattern(C, E, G, Bb, D, F)
    data object Dom13 : Pattern(C, E, G, Bb, D, F, A)

    data object Dim : Pattern(C, Ds, Fs)
    data object Dim7 : Pattern(C, Ds, Fs, A)
    data object M7B5 : Pattern(C, Ds, Fs, Bb)

    data object Aug : Pattern(C, E, Gs)
    data object Aug7 : Pattern(C, E, Gs, Bb)

    data object Sus2 : Pattern(C, D, G)
    data object Sus4 : Pattern(C, F, G)
    data object Sus74 : Pattern(C, F, G, Bb)
    data object Add9 : Pattern(C, E, G, D)
    data object Add11 : Pattern(C, E, G, F)

    class Custom(vararg notes: Note) : Pattern(*notes)
}

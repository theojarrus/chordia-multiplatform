package com.neotive.chordia.core.model

enum class Note {
    C, Cs, D, Ds, E, F, Fs, G, Gs, A, Bb, B;

    val isHalftone: Boolean get() = name.length > 1

    fun difference(note: Note): Int {
        return note.ordinal - ordinal
    }

    fun pitched(pitch: Int): Note {
        return enumValues<Note>().run { get((indexOf(this@Note) + pitch).mod(size)) }
    }
}

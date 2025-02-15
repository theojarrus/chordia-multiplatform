package com.neotive.chordia.generation.api.model

import com.neotive.chordia.core.model.Note

data class NotePositions(
    val note: Note,
    val positions: List<Int>
)

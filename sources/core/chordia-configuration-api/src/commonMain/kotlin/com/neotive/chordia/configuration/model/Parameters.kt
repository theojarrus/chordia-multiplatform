package com.neotive.chordia.configuration.model

import com.neotive.chordia.core.model.Instrument
import com.neotive.chordia.core.model.Note
import com.neotive.chordia.core.model.Pattern

data class Parameters(
    val instrument: Instrument,
    val pattern: Pattern,
    val tonic: Note
)

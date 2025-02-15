package com.neotive.chordia.sample.android.ui.screens.main

import com.neotive.chordia.core.model.Drop
import com.neotive.chordia.core.model.Instrument
import com.neotive.chordia.core.model.Length
import com.neotive.chordia.core.model.Note
import com.neotive.chordia.core.model.Pattern
import com.neotive.chordia.core.model.Type.Keys
import com.neotive.chordia.core.model.Type.Strings
import com.neotive.chordia.sample.android.ui.model.Option
import com.neotive.chordia.sample.android.ui.model.Option.MultipleOption

object OptionsList {

    fun instrumentOptions(instrument: Instrument, update: (Instrument) -> Unit): List<Option> {
        return listOf(
            MultipleOption(
                title = "Instrument",
                values = listOf(Strings, Keys),
                active = { instrument.type },
                shortFormatter = { it::class.simpleName.orEmpty() },
                onSet = { update(instrument.copy(type = it)) }
            ),
            MultipleOption(
                title = "Length",
                values = listOf(
                    Length.Guitar.Standard,
                    Length.Bass.Standard,
                    Length.Ukulele.Standard,
                    Length.Piano.Standard,
                    Length.Kalimba.Twelve
                ),
                active = { instrument.length },
                shortFormatter = {
                    it::class.qualifiedName
                        .orEmpty()
                        .split(Length::class.qualifiedName.orEmpty())
                        .last()
                        .replaceFirst(".", "")
                },
                longFormatter = {
                    it::class.qualifiedName
                        .orEmpty()
                        .split(Length::class.qualifiedName.orEmpty())
                        .last()
                        .replaceFirst(".", "")
                        .plus(" (${it.value})")
                },
                onSet = { update(instrument.copy(length = it)) }
            ),
            MultipleOption(
                title = "Drop",
                values = listOf(
                    Drop.Guitar.Standard,
                    Drop.Bass.Standard,
                    Drop.Ukulele.Standard,
                    Drop.Piano.Standard,
                    Drop.Kalimba.GTwelve
                ),
                active = { instrument.drop },
                shortFormatter = {
                    it::class.qualifiedName
                        .orEmpty()
                        .split(Drop::class.qualifiedName.orEmpty())
                        .last()
                        .replaceFirst(".", "")
                },
                longFormatter = {
                    it::class.qualifiedName
                        .orEmpty()
                        .split(Drop::class.qualifiedName.orEmpty())
                        .last()
                        .replaceFirst(".", "")
                        .plus(" ${it.lines.map(Note::name)}")
                },
                onSet = { update(instrument.copy(drop = it)) }
            ),
            MultipleOption(
                title = "Presets",
                values = listOf(
                    Pair("Guitar (Standart)", Instrument(Strings, Length.Guitar.Standard, Drop.Guitar.Standard)),
                    Pair("Piano (Standart)", Instrument(Keys, Length.Piano.Standard, Drop.Piano.Standard))
                ),
                active = { null },
                shortFormatter = { it.first },
                onSet = { update(it.second) }
            )
        )
    }

    fun chordOptions(
        pattern: Pattern,
        tonic: Note,
        updatePattern: (Pattern) -> Unit,
        updateNote: (Note) -> Unit
    ): List<Option> {
        return listOf(
            MultipleOption(
                title = "Pattern",
                values = listOf(
                    Pattern.Major,
                    Pattern.Maj6,
                    Pattern.Maj69,
                    Pattern.Maj7,
                    Pattern.Maj9,
                    Pattern.Maj11,
                    Pattern.Maj13,
                    Pattern.Minor,
                    Pattern.Min6,
                    Pattern.Min7,
                    Pattern.Min9,
                    Pattern.Min11,
                    Pattern.Min13,
                    Pattern.MMaj,
                    Pattern.Dom7,
                    Pattern.Dom9,
                    Pattern.Dom11,
                    Pattern.Dom13,
                    Pattern.Dim,
                    Pattern.Dim7,
                    Pattern.M7B5,
                    Pattern.Aug,
                    Pattern.Aug7,
                    Pattern.Sus2,
                    Pattern.Sus4,
                    Pattern.Sus74,
                    Pattern.Add9,
                    Pattern.Add11
                ),
                active = { pattern },
                shortFormatter = { it.toString() },
                onSet = { updatePattern(it) }
            ),
            MultipleOption(
                title = "Tonic",
                values = Note.entries,
                active = { tonic },
                shortFormatter = { it.name },
                onSet = { updateNote(it) }
            )
        )
    }
}

package com.neotive.chordia.sample.web

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.neotive.chordia.common.invoke
import com.neotive.chordia.common.api.tool.Chordia
import com.neotive.chordia.configuration.model.Parameters
import com.neotive.chordia.core.model.Drop
import com.neotive.chordia.core.model.Instrument
import com.neotive.chordia.core.model.Length
import com.neotive.chordia.core.model.Note
import com.neotive.chordia.core.model.Pattern
import com.neotive.chordia.core.model.Type
import com.neotive.chordia.rendering.configuration.BaseRenderingComposerConfiguration
import kotlinx.browser.document
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.flexWrap
import org.jetbrains.compose.web.css.fontFamily
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.listStyleType
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.paddingLeft
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul
import org.jetbrains.compose.web.renderComposableInBody

@Composable
private fun ChordiaWebSampleApp() {
    val chordia = Chordia().apply {
        configurationManager.set(BaseRenderingComposerConfiguration::scale to 20f)
    }

    val instrument = Instrument(Type.Strings, Length.Guitar.Standard, Drop.Guitar.Standard)
    val pattern = Pattern.Major
    val note = Note.C
    val parameters = Parameters(instrument, pattern, note)

    val items = chordia.chordify(parameters)

    Div(
        attrs = {
            style {
                display(DisplayStyle.Flex)
                alignItems(AlignItems.Center)
                height(60.px)
                width(100.percent)
                background("lightslategray")
                borderRadius(15.px)
            }
        }
    ) {
        Div(
            attrs = {
                style {
                    paddingLeft(16.px)
                    color(Color.white)
                    fontSize(1.4.em)
                    fontWeight("bold")
                    fontFamily("system-ui")
                }
                onClick { }
            }
        ) {
            Text("Chordia")
        }
    }
    Ul(
        attrs = {
            style {
                padding(0.px)
                display(DisplayStyle.Flex)
                justifyContent(JustifyContent.SpaceBetween)
                flexWrap(FlexWrap.Wrap)
                listStyleType("none")
            }
        }
    ) {
        items.values.forEachIndexed { index, item ->
            Li(attrs = { id(index.toString()) })
            LaunchedEffect(Unit) {
                document.getElementById(index.toString())?.innerHTML = item.svg
            }
        }
    }
}

fun main() {
    renderComposableInBody { ChordiaWebSampleApp() }
}

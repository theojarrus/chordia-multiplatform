import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun ChordiaDesktopSampleApp() {

    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        Column {
            val painter = loadSvgPainter(svggg.byteInputStream(), Density(1f))
            Image(painter = painter, contentDescription = null, modifier = Modifier.size(200.dp))

            Button(
                onClick = { text = "Hello, Desktop!" }
            ) {
                Text(text)
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) { ChordiaDesktopSampleApp() }
}

private val svggg = """
    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 70.0 70.0" fill="none">
    <rect x="10.0" y="10.0" width="50.0" height="50.0" stroke-width="1.0" rx="2.0" fill="none" />
    <line x1="10.0" y1="20.0" x2="60.0" y2="20.0" stroke-width="1.0" stroke="lightgray" />
    <line x1="10.0" y1="30.0" x2="60.0" y2="30.0" stroke-width="1.0" stroke="lightgray" />
    <line x1="10.0" y1="40.0" x2="60.0" y2="40.0" stroke-width="1.0" stroke="lightgray" />
    <line x1="10.0" y1="50.0" x2="60.0" y2="50.0" stroke-width="1.0" stroke="lightgray" />
    <line x1="20.0" y1="10.0" x2="20.0" y2="60.0" stroke-width="1.0" stroke="black" />
    <line x1="30.0" y1="10.0" x2="30.0" y2="60.0" stroke-width="1.0" stroke="black" />
    <line x1="40.0" y1="10.0" x2="40.0" y2="60.0" stroke-width="1.0" stroke="black" />
    <line x1="50.0" y1="10.0" x2="50.0" y2="60.0" stroke-width="1.0" stroke="black" />
    <rect x="10.0" y="10.0" width="50.0" height="50.0" stroke-width="1.0" rx="2.0" stroke="black" />
    <text x="0.0" y="15.0" font-size="3.5" dominant-baseline="central" font-family="roboto" fill="black" >5</text>
    <svg viewBox="0 0 5.4 5.4" x="37.3" y="1.3" width="5.4" height="5.4" overflow="visible"><line x1="0.0" y1="0.0" x2="5.4" y2="5.4" stroke-width="1.0" stroke="black" stroke-linecap="round" />
    <line x1="5.4" y1="0.0" x2="0.0" y2="5.4" stroke-width="1.0" stroke="black" stroke-linecap="round" />
    </svg>
    <svg viewBox="0 0 5.4 5.4" x="47.3" y="1.3" width="5.4" height="5.4" overflow="visible"><line x1="0.0" y1="0.0" x2="5.4" y2="5.4" stroke-width="1.0" stroke="black" stroke-linecap="round" />
    <line x1="5.4" y1="0.0" x2="0.0" y2="5.4" stroke-width="1.0" stroke="black" stroke-linecap="round" />
    </svg>
    <svg viewBox="0 0 5.4 5.4" x="57.3" y="1.3" width="5.4" height="5.4" overflow="visible"><line x1="0.0" y1="0.0" x2="5.4" y2="5.4" stroke-width="1.0" stroke="black" stroke-linecap="round" />
    <line x1="5.4" y1="0.0" x2="0.0" y2="5.4" stroke-width="1.0" stroke="black" stroke-linecap="round" />
    </svg>
    <circle cx="10.0" cy="45.0" r="3.5" stroke-width="0.0" fill="black" />
    <text x="10.0" y="45.0" font-size="3.5" dominant-baseline="central" font-family="roboto" fill="white" text-anchor="middle">3</text>
    <circle cx="20.0" cy="35.0" r="3.5" stroke-width="0.0" fill="black" />
    <text x="20.0" y="35.0" font-size="3.5" dominant-baseline="central" font-family="roboto" fill="white" text-anchor="middle">2</text>
    <circle cx="30.0" cy="15.0" r="3.5" stroke-width="0.0" fill="black" />
    <text x="30.0" y="15.0" font-size="3.5" dominant-baseline="central" font-family="roboto" fill="white" text-anchor="middle">1</text>
    </svg>
""".trimIndent()

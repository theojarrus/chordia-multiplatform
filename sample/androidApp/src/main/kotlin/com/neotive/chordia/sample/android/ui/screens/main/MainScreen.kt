package com.neotive.chordia.sample.android.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.neotive.chordia.configuration.model.Parameters
import com.neotive.chordia.core.model.Drop
import com.neotive.chordia.core.model.Instrument
import com.neotive.chordia.core.model.Length
import com.neotive.chordia.core.model.Note
import com.neotive.chordia.core.model.Pattern
import com.neotive.chordia.core.model.Type.Strings
import com.neotive.chordia.rendering.api.model.shape.HexColor
import com.neotive.chordia.sample.android.ui.di.Component
import com.neotive.chordia.sample.android.ui.elements.CellDefaults
import com.neotive.chordia.sample.android.ui.elements.ColorCell
import com.neotive.chordia.sample.android.ui.elements.ProgressButton
import com.neotive.chordia.sample.android.ui.elements.TextCell
import com.neotive.chordia.sample.android.ui.elements.Toolbar
import com.neotive.chordia.sample.android.ui.model.Option
import com.neotive.chordia.sample.android.ui.model.Option.MultipleOption
import com.neotive.chordia.sample.android.ui.screens.gallery.GalleryScreen
import com.neotive.chordia.sample.android.ui.screens.settings.SettingsScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainScreen : Screen {

    @Composable
    override fun Content() {
        val coroutineScope = rememberCoroutineScope(getContext = { Dispatchers.Default })
        val snackbarHostState: SnackbarHostState by remember { mutableStateOf(SnackbarHostState()) }
        var instrument: Instrument by remember {
            mutableStateOf(
                Instrument(
                    Strings,
                    Length.Guitar.Standard,
                    Drop.Guitar.Standard
                )
            )
        }
        var pattern: Pattern by remember { mutableStateOf(Pattern.Major) }
        var tonic: Note by remember { mutableStateOf(Note.C) }

        Scaffold(
            bottomBar = { ContentBottomAppBar(coroutineScope, instrument, pattern, tonic) },
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(
                    horizontal = 16.dp,
                    vertical = 32.dp,
                )
            ) {
                item { Toolbar(title = "Chordia", badge = "by neotive") }
                item {
                    GenerationOptions(
                        coroutineScope,
                        OptionsList.chordOptions(
                            pattern,
                            tonic,
                            { pattern = it },
                            { tonic = it }
                        )
                    )
                }
                item {
                    GenerationOptions(
                        coroutineScope,
                        OptionsList.instrumentOptions(instrument) { instrument = it }
                    )
                }
                item { RenderingOptions(coroutineScope, snackbarHostState) }
            }
        }
    }

    @Composable
    private fun GenerationOptions(coroutineScope: CoroutineScope, options: List<Option>) {
        val bottomSheetNavigator = LocalBottomSheetNavigator.current
        Card(modifier = Modifier.fillMaxWidth()) {
            options
                .filterIsInstance<MultipleOption<Any>>()
                .forEachIndexed { index, option ->
                    TextCell(
                        title = option.title,
                        value = option.active()?.let { option.shortFormatter(it) },
                        paddingValues = when (index) {
                            0 -> CellDefaults.firstCellPadding()
                            options.lastIndex -> CellDefaults.lastCellPadding()
                            else -> CellDefaults.defaultCellPadding()
                        },
                        onClick = {
                            coroutineScope.launch {
                                Component.option = option
                                bottomSheetNavigator.show(SettingsScreen)
                            }
                        }
                    )
                }
        }
    }

    @Composable
    private fun RenderingOptions(
        coroutineScope: CoroutineScope,
        snackbarHostState: SnackbarHostState
    ) {
        Card(modifier = Modifier.fillMaxWidth()) {
            ColorCell(
                title = "Colors",
                values = listOf(
                    Color(HexColor.WHITE.color.code.toColorInt()),
                    Color(HexColor.LIGHT_GRAY.color.code.toColorInt()),
                    Color(HexColor.LIGHT_SLATE_GRAY.color.code.toColorInt())
                ),
                onClick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Currently not supported in sample app")
                    }
                }
            )
        }
    }

    @Composable
    private fun ContentBottomAppBar(
        coroutineScope: CoroutineScope,
        instrument: Instrument,
        pattern: Pattern,
        tonic: Note
    ) {
        val parameters = Parameters(instrument, pattern, tonic)
        val bottomSheetNavigator = LocalBottomSheetNavigator.current
        BottomAppBar(
            actions = {
                IconButton(
                    onClick = {
                        Component.externalUrlNavigator.navigate("https://github.com/neotive")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.AccountCircle,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(
                    onClick = {
                        Component.externalUrlNavigator.navigate("https://github.com/theojarrus/chordia-multiplatform")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            },
            floatingActionButton = {
                var isLoading: Boolean by remember { mutableStateOf(false) }
                ProgressButton(
                    text = { Text("Generate") },
                    icon = { Icon(Icons.Default.Add, null) },
                    isLoading = isLoading,
                    onClick = {
                        coroutineScope
                            .takeUnless { isLoading }
                            ?.launch {
                                withContext(Dispatchers.IO) {
                                    isLoading = true
                                    val images = Component.chordia.chordify(parameters).values.toList()
                                    Component.images = images
                                    bottomSheetNavigator.show(GalleryScreen)
                                }
                            }
                            ?.invokeOnCompletion { isLoading = false }
                    },
                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                )
            }
        )
    }
}

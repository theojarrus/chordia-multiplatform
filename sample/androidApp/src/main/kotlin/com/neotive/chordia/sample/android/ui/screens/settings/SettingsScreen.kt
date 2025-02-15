package com.neotive.chordia.sample.android.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.neotive.chordia.androidApp.R
import com.neotive.chordia.sample.android.ui.di.Component
import com.neotive.chordia.sample.android.ui.elements.Button
import com.neotive.chordia.sample.android.ui.elements.DropdownTextField

object SettingsScreen : Screen {

    private fun readResolve(): Any = SettingsScreen

    @Composable
    override fun Content() {
        val bottomSheetNavigator = LocalBottomSheetNavigator.current
        Component.option?.let { option ->
            var activeItem = option.active()
            Surface {
                Column(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .padding(horizontal = 16.dp, vertical = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = option.title,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    DropdownTextField(
                        initial = activeItem?.let { option.longFormatter(it) }.orEmpty(),
                        items = option.values.associateBy { option.longFormatter(it) },
                        onItemClick = { activeItem = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Button(
                        text = stringResource(R.string.screen_settings_primary_button_label),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            activeItem?.let {
                                if (it != option.active()) {
                                    option.onSet(it)
                                }
                            }
                            bottomSheetNavigator.hide()
                        }
                    )
                }
            }
        } ?: bottomSheetNavigator.hide()
    }
}

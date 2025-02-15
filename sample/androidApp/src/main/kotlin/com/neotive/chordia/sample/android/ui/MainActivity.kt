package com.neotive.chordia.sample.android.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.core.net.toUri
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import com.neotive.chordia.sample.android.ui.di.Component
import com.neotive.chordia.sample.android.ui.navigation.ExternalUrlObserver
import com.neotive.chordia.sample.android.ui.screens.main.MainScreen
import com.neotive.chordia.sample.android.ui.theme.ChordiaTheme

class MainActivity : AppCompatActivity(), ExternalUrlObserver {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Component.externalUrlNavigator.observe(this)
        Component.option = null
        enableEdgeToEdge()
        setContent {
            ChordiaTheme {
                BottomSheetNavigator {
                    Navigator(MainScreen())
                }
            }
        }
    }

    override fun navigateExternalUrl(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
    }
}

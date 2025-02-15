package com.neotive.chordia.sample.android.ui.di

import com.neotive.chordia.common.api.tool.Chordia
import com.neotive.chordia.common.invoke
import com.neotive.chordia.core.model.Type.Keys
import com.neotive.chordia.core.model.Type.Strings
import com.neotive.chordia.rendering.api.model.ChordiaImage
import com.neotive.chordia.rendering.configuration.BaseRenderingComposerConfiguration
import com.neotive.chordia.sample.android.ui.model.Option
import com.neotive.chordia.sample.android.ui.navigation.ExternalUrlNavigator
import com.neotive.chordia.sample.android.ui.navigation.ExternalUrlNavigatorImpl

object Component {

    val chordia: Chordia<ChordiaImage> by lazy {
        Chordia().apply {
            configurationManager.apply {
                set(Strings::class, BaseRenderingComposerConfiguration::scale to 100f)
                set(Keys::class, BaseRenderingComposerConfiguration::scale to 70f)
            }
        }
    }

    val externalUrlNavigator: ExternalUrlNavigator by lazy { ExternalUrlNavigatorImpl() }

    var images: List<ChordiaImage> = emptyList()

    var option: Option.MultipleOption<Any>? = null
}
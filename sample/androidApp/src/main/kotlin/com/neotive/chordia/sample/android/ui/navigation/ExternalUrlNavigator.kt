package com.neotive.chordia.sample.android.ui.navigation

interface ExternalUrlNavigator {

    fun navigate(url: String)

    fun observe(observer: ExternalUrlObserver)
}

class ExternalUrlNavigatorImpl : ExternalUrlNavigator {

    private val observers: MutableList<ExternalUrlObserver> = mutableListOf()

    override fun navigate(url: String) {
        observers.forEach { it.navigateExternalUrl(url) }
    }

    override fun observe(observer: ExternalUrlObserver) {
        observers.add(observer)
    }
}
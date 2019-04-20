package com.test.revoluttestapp

import com.test.revoluttestapp.presentation.di.component.DaggerApplicationComponent
import dagger.android.support.DaggerApplication

class App: DaggerApplication() {

    private val applicationInjector by lazy {
        DaggerApplicationComponent.builder()
            .application(this)
            .build()
    }

    override fun applicationInjector() = applicationInjector
}
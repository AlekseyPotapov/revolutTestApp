package com.test.revoluttestapp.presentation.di.component

import com.test.revoluttestapp.App
import com.test.revoluttestapp.presentation.di.module.ActivityModule
import com.test.revoluttestapp.presentation.di.module.ApplicationModule
import com.test.revoluttestapp.presentation.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    ActivityModule::class
])

@ApplicationScope
interface ApplicationComponent: AndroidInjector<App> {

    fun inject(application: ApplicationComponent)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun build(): ApplicationComponent
    }

}
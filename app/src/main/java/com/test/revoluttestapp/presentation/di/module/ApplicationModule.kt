package com.test.revoluttestapp.presentation.di.module

import android.content.Context
import com.test.revoluttestapp.App
import com.test.revoluttestapp.presentation.di.scope.ApplicationScope
import dagger.Binds
import dagger.Module

@Module(includes = [
    NetworkModule::class,
    ViewModelModule::class,
    InteractorModule::class,
    RepositoryModule::class,
    AdapterModule::class
])
abstract class ApplicationModule {

    @Binds
    @ApplicationScope
    abstract fun bindApplicationContext(application: App): Context

}
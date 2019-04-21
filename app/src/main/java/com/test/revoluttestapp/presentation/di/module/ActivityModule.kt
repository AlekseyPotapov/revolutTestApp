package com.test.revoluttestapp.presentation.di.module

import com.test.revoluttestapp.presentation.MainActivity
import com.test.revoluttestapp.presentation.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [
    AdapterModule::class
])
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}
package com.test.revoluttestapp.presentation.di.module

import com.test.revoluttestapp.presentation.MainActivity
import com.test.revoluttestapp.presentation.di.scope.ActivityScope
import com.test.revoluttestapp.presentation.di.scope.ApplicationScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

}
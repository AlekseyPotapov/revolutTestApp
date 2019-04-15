package com.test.revoluttestapp.presentation.di.module

import com.test.revoluttestapp.domain.ConverterInteractor
import com.test.revoluttestapp.domain.ConverterInteractorImpl
import com.test.revoluttestapp.domain.ConverterRepository
import com.test.revoluttestapp.presentation.di.scope.ActivityScope
import com.test.revoluttestapp.presentation.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    @ApplicationScope
    fun provideConverterInteractor(repository: ConverterRepository): ConverterInteractor {
        return ConverterInteractorImpl(repository)
    }

}
package com.test.revoluttestapp.presentation.di.module

import com.test.revoluttestapp.data.ConverterRepositoryImpl
import com.test.revoluttestapp.data.source.NetworkSource
import com.test.revoluttestapp.domain.ConverterRepository
import com.test.revoluttestapp.presentation.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    @ApplicationScope
    fun provideConverterRepository(networkSource: NetworkSource): ConverterRepository {
        return ConverterRepositoryImpl(networkSource)
    }

}
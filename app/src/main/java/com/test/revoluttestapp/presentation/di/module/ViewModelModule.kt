package com.test.revoluttestapp.presentation.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.revoluttestapp.presentation.di.key.ViewModelKey
import com.test.revoluttestapp.presentation.di.scope.ApplicationScope
import com.test.revoluttestapp.presentation.viewmodel.ConverterViewModel
import com.test.revoluttestapp.presentation.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ConverterViewModel::class)
    @ApplicationScope
    abstract fun bindConverterViewModel(viewModel: ConverterViewModel): ViewModel

    @Binds
    @ApplicationScope
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
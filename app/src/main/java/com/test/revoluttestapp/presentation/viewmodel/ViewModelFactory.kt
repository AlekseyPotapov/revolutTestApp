package com.test.revoluttestapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

//class ViewModelFactory @Inject constructor(
//    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
//) : ViewModelProvider.Factory {
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        val viewModelProvider = viewModels[modelClass]
//            ?: throw IllegalArgumentException("model class $modelClass not found")
//        return viewModelProvider.get() as T
//    }
//
//}

class ViewModelFactory @Inject constructor(
        private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.find {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("Unregistered ViewModel class: $modelClass.")

        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}

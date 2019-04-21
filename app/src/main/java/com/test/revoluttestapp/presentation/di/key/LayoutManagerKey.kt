package com.test.revoluttestapp.presentation.di.key

import androidx.recyclerview.widget.RecyclerView
import dagger.MapKey
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class LayoutManagerKey(val value: KClass<out RecyclerView.LayoutManager>)

package com.test.revoluttestapp.presentation.di.module

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.test.revoluttestapp.presentation.di.scope.ActivityScope
import com.test.revoluttestapp.presentation.di.scope.ApplicationScope
import com.test.revoluttestapp.presentation.recycler.adapter.CurrencyListAdapter
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class AdapterModule {

    @Provides
    @ApplicationScope
    fun provideLinearLayoutManager(context: Context): LinearLayoutManager {
        return LinearLayoutManager(context)
    }

}
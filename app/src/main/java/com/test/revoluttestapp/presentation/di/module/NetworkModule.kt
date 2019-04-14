package com.test.revoluttestapp.presentation.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.test.revoluttestapp.presentation.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    @ApplicationScope
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @ApplicationScope
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideGsonConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    @ApplicationScope
    fun provideBackEndRetrofit(converterFactory: Converter.Factory,
                               httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://revolut.duckdns.org/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(converterFactory)
            .client(httpClient)
            .build()
    }
}
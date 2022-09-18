package com.marcospb.marvelapp.di

import android.content.Context
import com.marcospb.marvelapp.BuildConfig
import com.marcospb.marvelapp.data.remote.MarvelApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import javax.xml.datatype.DatatypeConstants.SECONDS
import kotlin.time.DurationUnit


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val CONNECT_TIMEOUT = 20L
    private const val READ_TIMEOUT = 60L
    private const val WRITE_TIMEOUT = 120L

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {

        return OkHttpClient.Builder()
            //.addInterceptor(httpLoggingInterceptor)
            //.addInterceptor(NetworkConnectionInterceptor(context))
            .build()
    }


    @Provides
    @Singleton
    fun providesMarvelService(httpClient: OkHttpClient): MarvelApiService {
        return Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelApiService::class.java)

    }


}
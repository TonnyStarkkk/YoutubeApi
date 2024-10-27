package com.example.youtubeapi.di

import com.example.youtubeapi.data.api.ApiService
import com.example.youtubeapi.data.repository.Repository
import com.example.youtubeapi.ui.fragments.playlists.PlaylistsViewModel
import okhttp3.OkHttpClient
import com.example.youtubeapi.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule: Module = module {

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_KEY)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder()
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
            )
            .build()
    }

    factory {
        get<Retrofit>().create(ApiService::class.java)
    }

    factory {
        Repository(get())
    }

    viewModel {
        PlaylistsViewModel(get())
    }
}
package br.com.android.commons.di

import br.com.android.commons.BuildConfig
import br.com.android.commons.data.service.GitApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

fun provideGitApiService(retrofit: Retrofit): GitApiService {
    return retrofit.create(GitApiService::class.java)
}

fun provideRetrofit(baseApiUrl: String, timeOutInSeconds: Long = 30L): Retrofit {
    val httpClient = OkHttpClient.Builder()
        .connectTimeout(timeOutInSeconds, TimeUnit.SECONDS)
        .writeTimeout(timeOutInSeconds, TimeUnit.SECONDS)
        .readTimeout(timeOutInSeconds, TimeUnit.SECONDS)
        .addInterceptor(provideHttpLoggingInterceptor())
        .build()

    return Retrofit
        .Builder()
        .client(httpClient)
        .baseUrl(baseApiUrl)
        .addConverterFactory(JacksonConverterFactory.create())
        .build()
}

private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
            setLevel(HttpLoggingInterceptor.Level.BASIC)
        }
    }
}
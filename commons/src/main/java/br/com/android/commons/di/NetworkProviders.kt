package br.com.android.commons.di

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

fun provideRetrofit(baseApiUrl: String, timeOutInSeconds: Long = 30L): Retrofit {
    val httpClient = OkHttpClient.Builder()
        .connectTimeout(timeOutInSeconds, TimeUnit.SECONDS)
        .writeTimeout(timeOutInSeconds, TimeUnit.SECONDS)
        .readTimeout(timeOutInSeconds, TimeUnit.SECONDS)
        .build()

    return Retrofit
        .Builder()
        .client(httpClient)
        .baseUrl(baseApiUrl)
        .addConverterFactory(JacksonConverterFactory.create())
        .build()
}

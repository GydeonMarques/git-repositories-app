package br.com.android.commons.di

import br.com.android.commons.BuildConfig
import org.koin.dsl.module

val gitNetworkModule = module(override = true) {
    single { provideRetrofit(BuildConfig.BASE_API_URL) }
}

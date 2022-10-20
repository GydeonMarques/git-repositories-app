package br.com.android.git.repositories.singleton

import android.app.Application
import br.com.android.commons.di.gitNetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class GitApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@GitApplication)
            androidLogger()
            modules(
                listOf(
                    gitNetworkModule
                )
            )
        }
    }
}
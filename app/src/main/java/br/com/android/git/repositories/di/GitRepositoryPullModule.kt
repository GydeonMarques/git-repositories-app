package br.com.android.git.repositories.di

import br.com.android.git.repositories.presentation.repository_pulls.viewmodel.GitRepositoryPullViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val gitRepositoryPullModule = module(override = true) {
    viewModel { GitRepositoryPullViewModel(get(), get()) }
}

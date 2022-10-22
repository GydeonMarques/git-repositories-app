package br.com.android.git.repositories.di

import br.com.android.git.repositories.presentation.repository_pulls.viewmodel.GitRepositoryPullsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val gitRepositoryPullsModule = module(override = true) {
    viewModel { GitRepositoryPullsViewModel(get(), get()) }
}

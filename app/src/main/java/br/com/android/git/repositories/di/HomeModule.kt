package br.com.android.git.repositories.di

import br.com.android.git.repositories.data.repository.GitRepository
import br.com.android.git.repositories.data.repository.GitRepositoryImpl
import br.com.android.git.repositories.domain.GitRepositoryUseCase
import br.com.android.git.repositories.domain.GitRepositoryUseCaseImpl
import br.com.android.git.repositories.presentation.repository_list.viewmodel.GitRepositoryListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val homeModule = module(override = true) {
    viewModel { GitRepositoryListViewModel(get()) }
    factory<GitRepository> { GitRepositoryImpl(get()) }
    factory<GitRepositoryUseCase> { GitRepositoryUseCaseImpl(get()) }
}

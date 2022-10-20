package br.com.android.git.repositories.domain

import br.com.android.commons.data.models.GitRepositoryWrapperModel
import br.com.android.commons.data.models.Result
import br.com.android.git.repositories.data.GitRepository

internal class GitRepositoryUseCaseImpl(private val repository: GitRepository) : GitRepositoryUseCase {

    override suspend fun loadAllPublicRepositories(): Result<GitRepositoryWrapperModel> {
        return repository.loadAllPublicRepositories()
    }
}
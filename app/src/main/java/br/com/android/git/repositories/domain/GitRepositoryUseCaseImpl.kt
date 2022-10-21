package br.com.android.git.repositories.domain

import br.com.android.commons.data.models.GitRepositoryPageModel
import br.com.android.commons.data.models.Result
import br.com.android.commons.data.models.toModel
import br.com.android.git.repositories.data.GitRepository

internal class GitRepositoryUseCaseImpl(private val repository: GitRepository) : GitRepositoryUseCase {

    override suspend fun loadAllPublicRepositories(): Result<GitRepositoryPageModel> {
        return when (val response = repository.loadAllPublicRepositories()) {
            is Result.Success -> Result.Success(response.data.toModel())
            is Result.Error -> response
        }
    }
}
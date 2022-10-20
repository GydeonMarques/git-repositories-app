package br.com.android.git.repositories.domain

import br.com.android.commons.data.models.GitRepositoryWrapperModel
import br.com.android.commons.data.models.Result

internal interface GitRepositoryUseCase {
    suspend fun loadAllPublicRepositories(): Result<GitRepositoryWrapperModel>
}
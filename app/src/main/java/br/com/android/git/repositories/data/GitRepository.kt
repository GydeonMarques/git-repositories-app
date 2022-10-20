package br.com.android.git.repositories.data

import br.com.android.commons.data.models.GitRepositoryWrapperModel
import br.com.android.commons.data.models.Result

internal interface GitRepository {
    suspend fun loadAllPublicRepositories(): Result<GitRepositoryWrapperModel>
}
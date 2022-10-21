package br.com.android.git.repositories.data

import br.com.android.commons.data.models.GitRepositoryPageResponse
import br.com.android.commons.data.models.Result

internal interface GitRepository {
    suspend fun loadAllPublicRepositories(): Result<GitRepositoryPageResponse>
}
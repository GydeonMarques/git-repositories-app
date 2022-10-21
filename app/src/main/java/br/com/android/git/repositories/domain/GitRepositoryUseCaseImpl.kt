package br.com.android.git.repositories.domain

import androidx.paging.PagingData
import br.com.android.commons.data.models.GitRepositoryDataModel
import br.com.android.commons.data.models.GitRepositoryPullsModel
import br.com.android.commons.data.models.GitRepositoryPullsRequest
import br.com.android.commons.util.PageParamsRequest
import br.com.android.git.repositories.data.repository.GitRepository
import kotlinx.coroutines.flow.Flow

internal class GitRepositoryUseCaseImpl(
    private val repository: GitRepository
) : GitRepositoryUseCase {

    override suspend fun loadAllPublicRepositories(request: PageParamsRequest): Flow<PagingData<GitRepositoryDataModel>> {
        return repository.loadAllPublicRepositories(request)
    }

    override suspend fun loadAllPullsOfRepository(request: GitRepositoryPullsRequest): Flow<PagingData<GitRepositoryPullsModel>> {
        return repository.loadAllPullsOfRepository(request)
    }
}
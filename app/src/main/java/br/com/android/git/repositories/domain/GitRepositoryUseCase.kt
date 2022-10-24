package br.com.android.git.repositories.domain

import androidx.paging.PagingData
import br.com.android.commons.data.models.GitRepositoryDataModel
import br.com.android.commons.data.models.GitRepositoryPullModel
import br.com.android.commons.data.models.GitRepositoryPullsRequest
import br.com.android.commons.util.PageParamsRequest
import kotlinx.coroutines.flow.Flow

internal interface GitRepositoryUseCase {
    suspend fun loadAllPublicRepositories(request: PageParamsRequest): Flow<PagingData<GitRepositoryDataModel>>
    suspend fun loadAllPullsOfRepository(request: GitRepositoryPullsRequest): Flow<PagingData<GitRepositoryPullModel>>
}
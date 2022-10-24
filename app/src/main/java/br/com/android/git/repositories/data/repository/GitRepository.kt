package br.com.android.git.repositories.data.repository

import androidx.paging.PagingData
import br.com.android.commons.data.models.*
import br.com.android.commons.util.PageParamsRequest
import kotlinx.coroutines.flow.Flow

internal interface GitRepository {
    suspend fun loadAllPublicRepositories(request: PageParamsRequest): Flow<PagingData<GitRepositoryDataModel>>
    suspend fun loadAllPullsOfRepository(request: GitRepositoryPullsRequest): Flow<PagingData<GitRepositoryPullModel>>
}
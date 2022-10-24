package br.com.android.git.repositories.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.android.commons.data.models.GitRepositoryDataModel
import br.com.android.commons.data.models.GitRepositoryPullModel
import br.com.android.commons.data.models.GitRepositoryPullsRequest
import br.com.android.commons.data.service.GitApiService
import br.com.android.commons.util.PageParamsRequest
import br.com.android.git.repositories.data.pagging.GitRepositoryListPagingDataSource
import br.com.android.git.repositories.data.pagging.GitRepositoryPullPagingDataSource
import kotlinx.coroutines.flow.Flow


internal class GitRepositoryImpl(
    private val service: GitApiService,
) : GitRepository {

    override suspend fun loadAllPublicRepositories(request: PageParamsRequest): Flow<PagingData<GitRepositoryDataModel>> {
        return Pager(
            initialKey = request.initialPage,
            config = PagingConfig(
                pageSize = request.pageSize,
                prefetchDistance = request.prefectDistance
            ),
            pagingSourceFactory = {
                GitRepositoryListPagingDataSource(
                    service,
                    request
                )
            }
        ).flow
    }

    override suspend fun loadAllPullsOfRepository(request: GitRepositoryPullsRequest): Flow<PagingData<GitRepositoryPullModel>> {
        return Pager(
            initialKey = request.initialPage,
            config = PagingConfig(
                pageSize = request.pageSize,
                prefetchDistance = request.prefectDistance
            ),
            pagingSourceFactory = {
                GitRepositoryPullPagingDataSource(
                    service,
                    request
                )
            }
        ).flow
    }

}
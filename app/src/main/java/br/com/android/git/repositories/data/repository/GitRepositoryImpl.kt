package br.com.android.git.repositories.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.android.commons.data.models.GitRepositoryDataModel
import br.com.android.commons.data.service.GitApiService
import br.com.android.commons.util.PageParams
import br.com.android.git.repositories.data.pagging.GitRepositoryPagingDataSource
import kotlinx.coroutines.flow.Flow


internal class GitRepositoryImpl(
    private val service: GitApiService,
) : GitRepository {

    override suspend fun loadAllPublicRepositories(pageParams: PageParams): Flow<PagingData<GitRepositoryDataModel>> {
        return Pager(
            initialKey = pageParams.initialPage,
            config = PagingConfig(
                pageSize = pageParams.pageSize,
                prefetchDistance = pageParams.prefectDistance
            ),
            pagingSourceFactory = {
                GitRepositoryPagingDataSource(
                    service,
                    pageParams
                )
            }
        ).flow
    }

}
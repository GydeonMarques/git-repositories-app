package br.com.android.git.repositories.data.pagging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.android.commons.data.models.GitRepositoryPullModel
import br.com.android.commons.data.models.GitRepositoryPullsRequest
import br.com.android.commons.data.models.toModel
import br.com.android.commons.data.service.GitApiService

internal class GitRepositoryPullPagingDataSource(
    private val service: GitApiService,
    private val request: GitRepositoryPullsRequest
) : PagingSource<Long, GitRepositoryPullModel>() {

    override fun getRefreshKey(state: PagingState<Long, GitRepositoryPullModel>): Long {
        return state.anchorPosition?.toLong() ?: request.initialPage
    }

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, GitRepositoryPullModel> {
        return try {

            val currentPage = params.key ?: request.initialPage

            val response = service.loadAllPullsOfRepository(
                sort = request.sortBy,
                page = currentPage.toString(),
                language = request.query,
                pageSize = request.pageSize.toString(),
                username = request.username.lowercase(),
                repositoryName = request.repositoryName.lowercase()
            )

            when {
                response.body() != null -> {
                    LoadResult.Page(
                        data = response.body()?.map { it.toModel() } ?: emptyList(),
                        prevKey = if (currentPage <= request.initialPage) null else currentPage - 1,
                        nextKey = if (response.body()?.isEmpty() == true) null else currentPage + 1,
                    )
                }
                response.errorBody() != null -> {
                    LoadResult.Error(Exception(response.errorBody()?.string()))
                }
                else -> {
                    LoadResult.Error(Exception("Ocorreu um erro ao carregar os dados."))
                }
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
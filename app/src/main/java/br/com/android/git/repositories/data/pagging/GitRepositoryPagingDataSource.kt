package br.com.android.git.repositories.data.pagging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.android.commons.data.models.GitRepositoryDataModel
import br.com.android.commons.data.models.toModel
import br.com.android.commons.data.service.GitApiService
import br.com.android.commons.util.PageParamsRequest

internal class GitRepositoryPagingDataSource(
    private val service: GitApiService,
    private val request: PageParamsRequest,
) : PagingSource<Long, GitRepositoryDataModel>() {

    override fun getRefreshKey(state: PagingState<Long, GitRepositoryDataModel>): Long {
        return request.initialPage
    }

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, GitRepositoryDataModel> {
        return try {

            val currentPage = params.key ?: 0

            val response = service.loadAllPublicRepositories(
                sort = request.sortBy,
                language = request.query,
                page = currentPage.toString(),
                pageSize = request.pageSize.toString()
            )

            when {
                response.body() != null -> {
                    LoadResult.Page(
                        data = response.body()?.items?.map { it.toModel() } ?: emptyList(),
                        prevKey = if (currentPage == 0L) null else currentPage - 1,
                        nextKey = if (response.body()?.items?.isEmpty() == true) null else currentPage + 1
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
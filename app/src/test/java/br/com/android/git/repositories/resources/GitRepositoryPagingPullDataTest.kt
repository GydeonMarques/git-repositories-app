package br.com.android.git.repositories.resources

import androidx.paging.PagingSource
import br.com.android.commons.data.models.GitRepositoryPullModel

internal object GitRepositoryPagingPullDataTest {

    val loadParams = PagingSource.LoadParams.Refresh(
        key = 1L,
        loadSize = 10,
        placeholdersEnabled = false
    )

    val pageResultSuccess = PagingSource.LoadResult.Page<Long, GitRepositoryPullModel>(
        data = GitRepositoryPullDataModelTest.gitRepositoryPullsDataModelList,
        prevKey = 0,
        nextKey = 1
    )

}
package br.com.android.git.repositories.resources

import androidx.paging.PagingSource
import br.com.android.commons.data.models.GitRepositoryPullsModel

internal object GitRepositoryPagingPullsDataTest {

    val loadParams = PagingSource.LoadParams.Refresh(
        key = 1L,
        loadSize = 10,
        placeholdersEnabled = false
    )

    val pageResultSuccess = PagingSource.LoadResult.Page<Long, GitRepositoryPullsModel>(
        data = GitRepositoryPullsDataModelTest.gitRepositoryPullsDataModelList,
        prevKey = 0,
        nextKey = 1
    )

}
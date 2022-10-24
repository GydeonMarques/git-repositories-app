package br.com.android.git.repositories.resources

import androidx.paging.PagingSource
import br.com.android.commons.data.models.GitRepositoryDataModel

internal object GitRepositoryPagingListDataTest {

    val loadParams = PagingSource.LoadParams.Refresh(
        key = 1L,
        loadSize = 10,
        placeholdersEnabled = false
    )

    val pageResultSuccess = PagingSource.LoadResult.Page<Long, GitRepositoryDataModel>(
        data = GitRepositoryDataModelTest.gitRepositoryDataModelList,
        prevKey = 0,
        nextKey = 1
    )

}
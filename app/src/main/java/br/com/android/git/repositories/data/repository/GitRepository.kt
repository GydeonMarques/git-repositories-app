package br.com.android.git.repositories.data.repository

import androidx.paging.PagingData
import br.com.android.commons.data.models.GitRepositoryDataModel
import br.com.android.commons.data.models.GitRepositoryPageModel
import br.com.android.commons.data.models.Result
import br.com.android.commons.util.PageParams
import kotlinx.coroutines.flow.Flow

internal interface GitRepository {
    suspend fun loadAllPublicRepositories(pageParams: PageParams): Flow<PagingData<GitRepositoryDataModel>>
}
package br.com.android.git.repositories.domain

import androidx.paging.PagingData
import br.com.android.commons.data.models.GitRepositoryDataModel
import br.com.android.commons.util.PageParams
import kotlinx.coroutines.flow.Flow

internal interface GitRepositoryUseCase {
    suspend fun loadAllPublicRepositories(pageParams: PageParams): Flow<PagingData<GitRepositoryDataModel>>
}
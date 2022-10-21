package br.com.android.git.repositories.domain

import androidx.paging.PagingData
import br.com.android.commons.data.models.GitRepositoryDataModel
import br.com.android.commons.util.PageParams
import br.com.android.git.repositories.data.repository.GitRepository
import kotlinx.coroutines.flow.Flow

internal class GitRepositoryUseCaseImpl(
    private val repository: GitRepository
) : GitRepositoryUseCase {

    override suspend fun loadAllPublicRepositories(pageParams: PageParams): Flow<PagingData<GitRepositoryDataModel>> {
        return repository.loadAllPublicRepositories(pageParams)
    }
}
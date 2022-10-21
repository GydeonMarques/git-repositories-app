package br.com.android.git.repositories.presentation.repository_pulls

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.android.commons.data.models.GitRepositoryPullsModel
import br.com.android.commons.data.models.GitRepositoryPullsRequest
import br.com.android.git.repositories.domain.GitRepositoryUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal class GitRepositoryPullsViewModel(
    private val useCase: GitRepositoryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<PagingData<GitRepositoryPullsModel>>(PagingData.empty())
    val state: Flow<PagingData<GitRepositoryPullsModel>> get() = _state

    fun loadAllPullsOfRepository(username: String, repositoryName: String) {
        val request = GitRepositoryPullsRequest(
            username = username,
            repositoryName = repositoryName
        )
        viewModelScope.launch {
            useCase.loadAllPullsOfRepository(request)
                .cachedIn(viewModelScope)
                .collectLatest {
                    _state.value = it
                }
        }
    }
}
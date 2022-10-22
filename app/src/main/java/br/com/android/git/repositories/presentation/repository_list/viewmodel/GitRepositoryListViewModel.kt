package br.com.android.git.repositories.presentation.repository_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.android.commons.data.models.GitRepositoryDataModel
import br.com.android.commons.util.PageParamsRequest
import br.com.android.git.repositories.domain.GitRepositoryUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


internal class GitRepositoryListViewModel constructor(
    private val useCase: GitRepositoryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<PagingData<GitRepositoryDataModel>>(PagingData.empty())
    val state: Flow<PagingData<GitRepositoryDataModel>> get() = _state

    init {
        loadAllPublicRepositories()
    }

    private fun loadAllPublicRepositories() {
        val request = PageParamsRequest(
            query = "language:Java",
            sortBy = "stars"
        )
        viewModelScope.launch {
            useCase.loadAllPublicRepositories(request)
                .cachedIn(viewModelScope)
                .collectLatest {
                    _state.value = it
                }
        }
    }
}
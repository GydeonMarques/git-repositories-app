package br.com.android.git.repositories.presentation.repository_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import br.com.android.commons.data.models.GitRepositoryDataModel
import br.com.android.commons.util.PageParams
import br.com.android.git.repositories.domain.GitRepositoryUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
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
        val params = PageParams(
            query = "language:Java",
            sortBy = "stars"
        )
        viewModelScope.launch {
            useCase.loadAllPublicRepositories(params).collect {
                _state.value = it
            }
        }
    }
}
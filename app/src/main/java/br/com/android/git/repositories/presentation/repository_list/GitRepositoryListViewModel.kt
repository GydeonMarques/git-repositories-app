package br.com.android.git.repositories.presentation.repository_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.android.commons.data.models.GitRepositoryPageModel
import br.com.android.commons.data.models.Result
import br.com.android.git.repositories.domain.GitRepositoryUseCase
import kotlinx.coroutines.launch


internal sealed class GitRepositoryState {
    object Loading : GitRepositoryState()
    data class Error(val error: Result.Error) : GitRepositoryState()
    data class Success(val data: GitRepositoryPageModel) : GitRepositoryState()
}

internal class GitRepositoryListViewModel constructor(
    private val useCase: GitRepositoryUseCase
) : ViewModel() {

    private val _state = MutableLiveData<GitRepositoryState>()
    val state: LiveData<GitRepositoryState> get() = _state

    fun loadAllPublicRepositories() {
        viewModelScope.launch() {
            when (val response = useCase.loadAllPublicRepositories()) {
                is Result.Success -> {
                    _state.value = GitRepositoryState.Success(data = response.data)
                }
                is Result.Error -> {
                    _state.value = GitRepositoryState.Error(error = response)
                }
            }
        }
    }
}
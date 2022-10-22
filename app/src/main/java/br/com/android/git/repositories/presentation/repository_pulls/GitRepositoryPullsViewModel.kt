package br.com.android.git.repositories.presentation.repository_pulls

import android.app.Application
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
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
    application: Application,
    private val useCase: GitRepositoryUseCase
) : AndroidViewModel(application) {

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

    fun openPullRequestPage(url: String){
            ContextCompat.startActivity(getApplication(), Intent(Intent.ACTION_VIEW).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                data = Uri.parse(url)
            }, null)
    }
}
package br.com.android.git.repositories.presentation.repository_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import br.com.android.commons.data.models.GitRepositoryWrapperModel
import br.com.android.commons.data.models.Result
import br.com.android.git.repositories.databinding.FragmentGitRepositoryListBinding
import br.com.android.git.repositories.di.homeModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class GitRepositoryListFragment : Fragment() {

    private lateinit var binding: FragmentGitRepositoryListBinding
    private val viewModel by viewModel<GitRepositoryListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(homeModule)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentGitRepositoryListBinding.inflate(inflater, container, false).run {
            binding = this
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadAllPublicRepositories()
    }

    private fun loadAllPublicRepositories() {
        viewModel.loadAllPublicRepositories()
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is GitRepositoryState.Loading -> renderLoading()
                is GitRepositoryState.Success -> renderSuccess(state.data)
                is GitRepositoryState.Error -> renderError(state.error)
            }
        }
    }

    private fun renderLoading() {
        changeLayoutVisibility(isLoading = true)
    }

    private fun renderSuccess(data: GitRepositoryWrapperModel) {
        changeLayoutVisibility(isSuccess = true)
    }

    private fun renderError(error: Result.Error) {
        changeLayoutVisibility(isError = true)
    }

    private fun changeLayoutVisibility(
        isLoading: Boolean = false,
        isSuccess: Boolean = false,
        isError: Boolean = false,
    ) {
        with(binding) {
            layoutError.root.isVisible = !isLoading && !isSuccess && isError
            layoutLoading.root.isVisible = !isError && !isSuccess && isLoading
            layoutGitRepositoryList.root.isVisible = !isLoading && !isError && isSuccess
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        loadKoinModules(homeModule)
    }
}
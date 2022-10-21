package br.com.android.git.repositories.presentation.repository_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import br.com.android.commons.util.PagingLoadStateAdapter
import br.com.android.git.repositories.databinding.FragmentGitRepositoryListBinding
import br.com.android.git.repositories.di.homeModule
import br.com.android.git.repositories.presentation.repository_list.adapter.GitRepositoryAdapter
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class GitRepositoryListFragment : Fragment() {

    private val navController: NavController by lazy { findNavController() }
    private lateinit var binding: FragmentGitRepositoryListBinding
    private val viewModel by viewModel<GitRepositoryListViewModel>()
    private val gitRepositoryAdapter: GitRepositoryAdapter by lazy {
        GitRepositoryAdapter()
    }

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
        setupView()
        setupObservables()
    }

    private fun setupView() {
        with(binding.layoutGitRepositoryList) {
            recyclerView.apply {
                postponeEnterTransition()
                viewTreeObserver.addOnPreDrawListener {
                    startPostponedEnterTransition()
                    true
                }
                adapter = gitRepositoryAdapter.run {
                    withLoadStateHeaderAndFooter(
                        header = PagingLoadStateAdapter(this),
                        footer = PagingLoadStateAdapter(this)
                    )
                }
            }
        }
    }

    private fun setupObservables() {
        gitRepositoryAdapter.apply {
            addOnItemClickListener {
                navController.navigate(
                    GitRepositoryListFragmentDirections.goToPullRequestOfRepository()
                )
            }

            addLoadStateListener {
                when (it.refresh) {
                    is LoadState.Error -> changeLayoutVisibility(isError = true)
                    is LoadState.Loading -> changeLayoutVisibility(isLoading = true)
                    is LoadState.NotLoading -> changeLayoutVisibility(isSuccess = true)
                }
            }

            lifecycleScope.launchWhenCreated {
                viewModel.state.collectLatest {
                    submitData(it)
                }
            }
        }
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
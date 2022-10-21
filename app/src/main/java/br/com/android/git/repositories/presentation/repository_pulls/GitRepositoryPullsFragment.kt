package br.com.android.git.repositories.presentation.repository_pulls

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import br.com.android.commons.util.PagingLoadStateAdapter
import br.com.android.git.repositories.databinding.FragmentGitRepositoryPullsBinding
import br.com.android.git.repositories.di.gitRepositoryPullsModule
import br.com.android.git.repositories.presentation.repository_pulls.adapter.GitRepositoryPullsAdapter
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class GitRepositoryPullsFragment : Fragment() {

    private val args by navArgs<GitRepositoryPullsFragmentArgs>()
    private lateinit var binding: FragmentGitRepositoryPullsBinding
    private val viewModel by viewModel<GitRepositoryPullsViewModel>()
    private val gitRepositoryPullsAdapter: GitRepositoryPullsAdapter by lazy {
        GitRepositoryPullsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(gitRepositoryPullsModule)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentGitRepositoryPullsBinding.inflate(inflater, container, false).run {
            binding = this
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservables()
        loadAllPullsOfRepository()
    }



    private fun setupView() {
        with(binding.layoutGitRepositoryPulls) {
            recyclerView.apply {
                postponeEnterTransition()
                viewTreeObserver.addOnPreDrawListener {
                    startPostponedEnterTransition()
                    true
                }
                adapter = gitRepositoryPullsAdapter.run {
                    withLoadStateHeaderAndFooter(
                        header = PagingLoadStateAdapter(this),
                        footer = PagingLoadStateAdapter(this)
                    )
                }
            }
        }
    }

    private fun setupObservables() {
        gitRepositoryPullsAdapter.apply {
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

    private fun loadAllPullsOfRepository(){
        viewModel.loadAllPullsOfRepository(args.username, args.repositoryName)
    }

    private fun changeLayoutVisibility(
        isLoading: Boolean = false,
        isSuccess: Boolean = false,
        isError: Boolean = false,
    ) {
        with(binding) {
            layoutError.root.isVisible = !isLoading && !isSuccess && isError
            layoutLoading.root.isVisible = !isError && !isSuccess && isLoading
            layoutGitRepositoryPulls.root.isVisible = !isLoading && !isError && isSuccess
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(gitRepositoryPullsModule)
    }
}
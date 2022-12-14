package br.com.android.git.repositories.presentation.repository_pulls.fragment

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
import br.com.android.git.repositories.databinding.FragmentGitRepositoryPullBinding
import br.com.android.git.repositories.di.gitRepositoryPullModule
import br.com.android.git.repositories.presentation.repository_pulls.viewmodel.GitRepositoryPullViewModel
import br.com.android.git.repositories.presentation.repository_pulls.adapter.GitRepositoryPullsAdapter
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class GitRepositoryPullFragment : Fragment() {

    private val args by navArgs<GitRepositoryPullFragmentArgs>()
    private lateinit var binding: FragmentGitRepositoryPullBinding
    private val viewModel by viewModel<GitRepositoryPullViewModel>()
    private val gitRepositoryPullsAdapter: GitRepositoryPullsAdapter by lazy {
        GitRepositoryPullsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(gitRepositoryPullModule)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentGitRepositoryPullBinding.inflate(inflater, container, false).run {
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

            addOnItemClickListener { model->
                model.pullURL?.let { viewModel.openPullRequestPage(it) }
            }

            addLoadStateListener {
                it.refresh
                when (it.refresh) {
                    is LoadState.Error -> changeLayoutVisibility(isError = true)
                    is LoadState.Loading -> changeLayoutVisibility(isLoading = true)
                    is LoadState.NotLoading -> changeLayoutVisibility(isSuccess = true, isEmpty = itemCount <= 0)
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
        isEmpty: Boolean = false,
        isError: Boolean = false,
    ) {
        with(binding) {
            layoutError.root.isVisible = !isLoading && !isSuccess && isError
            layoutLoading.root.isVisible = !isError && !isSuccess && isLoading
            layoutEmpty.root.isVisible =  !isLoading && !isError && isSuccess && isEmpty
            layoutGitRepositoryPulls.root.isVisible = !isLoading && !isError && isSuccess
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(gitRepositoryPullModule)
    }
}
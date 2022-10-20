package br.com.android.git.repositories.presentation.repository_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

    override fun onDestroy() {
        super.onDestroy()
        loadKoinModules(homeModule)
    }
}
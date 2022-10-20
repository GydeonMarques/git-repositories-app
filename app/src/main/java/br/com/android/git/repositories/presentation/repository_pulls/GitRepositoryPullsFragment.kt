package br.com.android.git.repositories.presentation.repository_pulls

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.android.git.repositories.databinding.FragmentGitRepositoryPullsBinding

class GitRepositoryPullsFragment : Fragment() {

    private lateinit var binding: FragmentGitRepositoryPullsBinding

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
}
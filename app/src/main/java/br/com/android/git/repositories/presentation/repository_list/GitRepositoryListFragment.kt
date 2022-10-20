package br.com.android.git.repositories.presentation.repository_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.android.git.repositories.databinding.FragmentGitRepositoryListBinding

class GitRepositoryListFragment : Fragment() {

    private lateinit var binding: FragmentGitRepositoryListBinding

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
}
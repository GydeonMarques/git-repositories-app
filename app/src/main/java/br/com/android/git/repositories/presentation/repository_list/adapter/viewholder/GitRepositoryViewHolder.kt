package br.com.android.git.repositories.presentation.repository_list.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.android.commons.data.models.GitRepositoryWrapperModel
import br.com.android.commons.util.loadImageByUrl
import br.com.android.git.repositories.databinding.LayoutGitRepositoryCardItemBinding

internal class GitRepositoryViewHolder(
    private val binding: LayoutGitRepositoryCardItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun getInstance(parent: ViewGroup): GitRepositoryViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = LayoutGitRepositoryCardItemBinding.inflate(inflater, parent, false)
            return GitRepositoryViewHolder(binding)
        }
    }

    fun bindView(model: GitRepositoryWrapperModel.GitRepositoryDataModel) {
        binding.apply {

            layoutGitRepositoryItem.apply {
                textViewRepositoryTitle.text = model.name
                textViewRepositoryDescription.text = model.description
            }

            layoutGitRepositoryUserItem.apply {
                imageViewUser.loadImageByUrl(model.owner.avatarUrl)
                textViewUsername.text = model.owner.login
                textViewFullName.text = model.fullName
            }

            layoutGitRepositoryForksItem.apply {
                textViewRepositoryForks.text = model.forksCount.toString()
                textViewRepositoryRates.text = model.watchersCount.toString()
            }
        }
    }
}

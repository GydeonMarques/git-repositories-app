package br.com.android.git.repositories.presentation.repository_pulls.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.android.commons.data.models.GitRepositoryPullsModel
import br.com.android.commons.util.loadImageByUrl
import br.com.android.git.repositories.databinding.LayoutGitRepositoryCardPullItemBinding

internal class GitRepositoryPullsViewHolder(
    private val binding: LayoutGitRepositoryCardPullItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun getInstance(
            parent: ViewGroup,
        ): GitRepositoryPullsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = LayoutGitRepositoryCardPullItemBinding.inflate(inflater, parent, false)
            return GitRepositoryPullsViewHolder(binding)
        }
    }

    fun bindView(model: GitRepositoryPullsModel) {
        binding.apply {

            layoutGitRepositoryItem.apply {
                textViewRepositoryTitle.text = model.title
                textViewRepositoryDescription.text = model.body
            }

            layoutGitRepositoryUserItem.apply {
                imageViewUser.loadImageByUrl(model.user.avatarUrl)
                textViewUsername.text = model.user.login
                textViewFullName.text = model.title
            }
        }
    }
}

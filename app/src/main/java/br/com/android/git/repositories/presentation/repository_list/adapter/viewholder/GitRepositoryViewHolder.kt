package br.com.android.git.repositories.presentation.repository_list.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.android.commons.data.models.GitRepositoryDataModel
import br.com.android.commons.util.loadImageByUrl
import br.com.android.git.repositories.databinding.LayoutGitRepositoryCardItemBinding

typealias OnRepositoryItemClickListener = ((model: GitRepositoryDataModel) -> Unit)?

internal class GitRepositoryViewHolder(
    private val binding: LayoutGitRepositoryCardItemBinding,
    private val onItemClickListener: OnRepositoryItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun getInstance(
            parent: ViewGroup,
            onItemClickListener: OnRepositoryItemClickListener
        ): GitRepositoryViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = LayoutGitRepositoryCardItemBinding.inflate(inflater, parent, false)
            return GitRepositoryViewHolder(binding, onItemClickListener)
        }
    }

    fun bindView(model: GitRepositoryDataModel) {
        binding.apply {

            onItemClickListener?.let { cardGitRepository.setOnClickListener { it(model) } }

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

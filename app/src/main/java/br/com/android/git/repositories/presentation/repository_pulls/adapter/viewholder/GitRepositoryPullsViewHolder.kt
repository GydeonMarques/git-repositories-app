package br.com.android.git.repositories.presentation.repository_pulls.adapter.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import br.com.android.commons.data.models.GitRepositoryPullsModel
import br.com.android.commons.util.convertFromUSFormatToDateBR
import br.com.android.commons.util.loadImageByUrl
import br.com.android.git.repositories.R
import br.com.android.git.repositories.databinding.LayoutGitRepositoryCardPullItemBinding

typealias OnRepositoryPullItemClickListener = ((model: GitRepositoryPullsModel) -> Unit)?

internal class GitRepositoryPullsViewHolder(
    private val context: Context,
    private val binding: LayoutGitRepositoryCardPullItemBinding,
    private val onItemClickListener: OnRepositoryPullItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun getInstance(
            parent: ViewGroup,
            onItemClickListener: OnRepositoryPullItemClickListener
        ): GitRepositoryPullsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = LayoutGitRepositoryCardPullItemBinding.inflate(inflater, parent, false)
            return GitRepositoryPullsViewHolder(parent.context, binding, onItemClickListener)
        }
    }

    fun bindView(model: GitRepositoryPullsModel) {
        binding.apply {

            onItemClickListener?.let { cardGitRepositoryPull.setOnClickListener { it(model) } }

            layoutGitRepositoryItem.apply {
                textViewRepositoryTitle.text = model.title
                textViewRepositoryDescription.text = model.body
                textViewRepositoryDescription.isVisible = model.body.isNotEmpty()
            }

            layoutGitRepositoryUserItem.apply {
                imageViewUser.loadImageByUrl(model.user.avatarUrl)
                textViewFullName.text = model.base.repository?.fullName.orEmpty()
                textViewUsername.text = model.user.login
            }

            textViewPrCreateAt.text = context.getString(
                R.string.create_at, model.createdAt.convertFromUSFormatToDateBR()
            )
        }
    }
}

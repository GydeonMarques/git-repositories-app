package br.com.android.git.repositories.presentation.repository_pulls.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import br.com.android.commons.data.models.GitRepositoryPullModel

internal class GitRepositoryPullDiffCallBack : DiffUtil.ItemCallback<GitRepositoryPullModel>() {
    override fun areItemsTheSame(
        oldItem: GitRepositoryPullModel,
        newItem: GitRepositoryPullModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: GitRepositoryPullModel,
        newItem: GitRepositoryPullModel
    ): Boolean {
        return oldItem == newItem
    }
}
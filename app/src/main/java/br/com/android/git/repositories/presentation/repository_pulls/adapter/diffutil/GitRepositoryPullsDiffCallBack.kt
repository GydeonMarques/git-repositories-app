package br.com.android.git.repositories.presentation.repository_pulls.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import br.com.android.commons.data.models.GitRepositoryPullsModel

internal class GitRepositoryPullsDiffCallBack : DiffUtil.ItemCallback<GitRepositoryPullsModel>() {
    override fun areItemsTheSame(
        oldItem: GitRepositoryPullsModel,
        newItem: GitRepositoryPullsModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: GitRepositoryPullsModel,
        newItem: GitRepositoryPullsModel
    ): Boolean {
        return oldItem == newItem
    }
}
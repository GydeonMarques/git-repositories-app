package br.com.android.git.repositories.presentation.repository_pulls.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import br.com.android.commons.data.models.GitRepositoryPullsModel
import br.com.android.git.repositories.presentation.repository_pulls.adapter.diffutil.GitRepositoryPullsDiffCallBack
import br.com.android.git.repositories.presentation.repository_pulls.adapter.viewholder.GitRepositoryPullsViewHolder

internal class GitRepositoryPullsAdapter : PagingDataAdapter<GitRepositoryPullsModel, GitRepositoryPullsViewHolder>(
    GitRepositoryPullsDiffCallBack()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepositoryPullsViewHolder {
        return GitRepositoryPullsViewHolder.getInstance(parent)
    }

    override fun onBindViewHolder(holder: GitRepositoryPullsViewHolder, position: Int) {
        getItem(position)?.let { holder.bindView(it) }
    }
}
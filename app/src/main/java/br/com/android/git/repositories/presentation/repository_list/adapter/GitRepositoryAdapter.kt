package br.com.android.git.repositories.presentation.repository_list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import br.com.android.commons.data.models.GitRepositoryWrapperModel.GitRepositoryDataModel
import br.com.android.git.repositories.presentation.repository_list.adapter.diffutil.GitRepositoryDiffCallBack
import br.com.android.git.repositories.presentation.repository_list.adapter.viewholder.GitRepositoryViewHolder

internal class GitRepositoryAdapter : ListAdapter<GitRepositoryDataModel, GitRepositoryViewHolder>(GitRepositoryDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepositoryViewHolder {
        return GitRepositoryViewHolder.getInstance(parent)
    }

    override fun onBindViewHolder(holder: GitRepositoryViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }
}
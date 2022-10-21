package br.com.android.git.repositories.presentation.repository_list.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import br.com.android.commons.data.models.GitRepositoryDataModel
import br.com.android.git.repositories.presentation.repository_list.adapter.diffutil.GitRepositoryDiffCallBack
import br.com.android.git.repositories.presentation.repository_list.adapter.viewholder.GitRepositoryViewHolder
import br.com.android.git.repositories.presentation.repository_list.adapter.viewholder.OnRepositoryItemClickListener

internal class GitRepositoryAdapter : PagingDataAdapter<GitRepositoryDataModel, GitRepositoryViewHolder>(GitRepositoryDiffCallBack()) {

    private var onItemClickListener: OnRepositoryItemClickListener = null

    fun addOnItemClickListener(onItemClickListener: OnRepositoryItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepositoryViewHolder {
        return GitRepositoryViewHolder.getInstance(parent, onItemClickListener)
    }

    override fun onBindViewHolder(holder: GitRepositoryViewHolder, position: Int) {
        getItem(position)?.let { holder.bindView(it) }
    }
}
package br.com.android.git.repositories.presentation.repository_list.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import br.com.android.commons.data.models.GitRepositoryWrapperModel.GitRepositoryDataModel

internal class GitRepositoryDiffCallBack : DiffUtil.ItemCallback<GitRepositoryDataModel>() {
    override fun areItemsTheSame(
        oldItem: GitRepositoryDataModel,
        newItem: GitRepositoryDataModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: GitRepositoryDataModel,
        newItem: GitRepositoryDataModel
    ): Boolean {
        return oldItem == newItem
    }
}
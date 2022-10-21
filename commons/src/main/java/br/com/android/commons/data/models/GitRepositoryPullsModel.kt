package br.com.android.commons.data.models

data class GitRepositoryPullsModel(
    val id: Long,
    val title: String,
    val body: String,
    val user: GitRepositoryOwnerModel
)
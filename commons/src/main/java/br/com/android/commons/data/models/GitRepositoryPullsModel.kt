package br.com.android.commons.data.models

data class GitRepositoryPullsModel(
    val id: Long,
    val title: String,
    val body: String,
    val number: Long,
    val createdAt: String,
    val base: GitRepositoryBaseModel,
    val user: GitRepositoryOwnerModel
)
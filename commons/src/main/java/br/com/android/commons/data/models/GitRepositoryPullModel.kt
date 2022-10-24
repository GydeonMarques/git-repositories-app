package br.com.android.commons.data.models

data class GitRepositoryPullModel(
    val id: Long,
    val title: String,
    val body: String,
    val number: Long,
    val pullURL: String?,
    val createdAt: String,
    val base: GitRepositoryBaseModel,
    val user: GitRepositoryOwnerModel
)
package br.com.android.commons.data.models


data class GitRepositoryWrapperModel(
    val totalCount: Long,
    val items: List<GitRepositoryDataModel>
) {
    data class GitRepositoryDataModel(
        val id: Long,
        val name: String,
        val fullName: String,
        val forksCount: Long,
        val description: String,
        val watchersCount: Long,
        val owner: GitRepositoryOwnerModel
    )

    data class GitRepositoryOwnerModel(
        val id: Long,
        val login: String,
        val avatarUrl: String,
    )
}
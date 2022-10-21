package br.com.android.commons.data.models

data class GitRepositoryDataModel(
    val id: Long,
    val name: String,
    val fullName: String,
    val forksCount: Long,
    val description: String,
    val watchersCount: Long,
    val owner: GitRepositoryOwnerModel
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GitRepositoryDataModel

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}

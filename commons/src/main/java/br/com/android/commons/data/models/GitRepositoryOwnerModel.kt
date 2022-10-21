package br.com.android.commons.data.models

data class GitRepositoryOwnerModel(
    val id: Long,
    val login: String,
    val avatarUrl: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GitRepositoryOwnerModel

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
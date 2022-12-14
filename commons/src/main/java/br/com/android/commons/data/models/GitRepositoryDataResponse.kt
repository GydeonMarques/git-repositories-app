package br.com.android.commons.data.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class GitRepositoryDataResponse(
    @JsonProperty("id") val id: Long?,
    @JsonProperty("name") val name: String?,
    @JsonProperty("full_name") val fullName: String?,
    @JsonProperty("forks_count") val forksCount: Long?,
    @JsonProperty("description") val description: String?,
    @JsonProperty("watchers_count") val watchersCount: Long?,
    @JsonProperty("owner") val owner: GitRepositoryOwnerResponse?
)

fun GitRepositoryDataResponse.toModel(): GitRepositoryDataModel {
    return GitRepositoryDataModel(
        id = id ?: 0,
        name = name ?: "",
        fullName = fullName ?: "",
        forksCount = forksCount ?: 0,
        description = description ?: "",
        watchersCount = watchersCount ?: 0,
        owner = GitRepositoryOwnerModel(
            id = owner?.id ?: 0,
            login = owner?.login ?: "",
            avatarUrl = owner?.avatarUrl ?: "",
        )
    )
}
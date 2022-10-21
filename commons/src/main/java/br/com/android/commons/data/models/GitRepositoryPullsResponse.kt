package br.com.android.commons.data.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class GitRepositoryPullsDataResponse(
    @JsonProperty("id") val id: Long?,
    @JsonProperty("body") val body: String?,
    @JsonProperty("title") val title: String?,
    @JsonProperty("user") val user: GitRepositoryOwnerResponse?,
)

fun GitRepositoryPullsDataResponse.toModel(): GitRepositoryPullsModel {
    return GitRepositoryPullsModel(
        id = id ?: 0L,
        title = title ?: "",
        body = body ?: "",
        user = GitRepositoryOwnerModel(
            id = user?.id ?: 0L,
            login = user?.login ?: "",
            avatarUrl = user?.avatarUrl ?: "",
        )
    )
}
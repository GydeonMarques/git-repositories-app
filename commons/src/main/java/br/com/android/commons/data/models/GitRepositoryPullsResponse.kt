package br.com.android.commons.data.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class GitRepositoryPullsDataResponse(
    @JsonProperty("id") val id: Long?,
    @JsonProperty("body") val body: String?,
    @JsonProperty("title") val title: String?,
    @JsonProperty("number") val number: Long?,
    @JsonProperty("html_url") val pullURL: String?,
    @JsonProperty("created_at") val createdAt: String?,
    @JsonProperty("base") val base: GitRepositoryBaseResponse?,
    @JsonProperty("user") val user: GitRepositoryOwnerResponse?,

)

fun GitRepositoryPullsDataResponse.toModel(): GitRepositoryPullsModel {
    return GitRepositoryPullsModel(
        id = id ?: 0L,
        title = title ?: "",
        body = body ?: "",
        number = number ?: 0,
        pullURL = pullURL ?: "",
        createdAt = createdAt ?: "",
        user = GitRepositoryOwnerModel(
            id = user?.id ?: 0L,
            login = user?.login ?: "",
            avatarUrl = user?.avatarUrl ?: "",
        ),
        base = GitRepositoryBaseModel(repository = base?.repository?.toModel()),
    )
}
package br.com.android.commons.data.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class GitRepositoryOwnerResponse(
    @JsonProperty("id") val id: Long?,
    @JsonProperty("login") val login: String?,
    @JsonProperty("avatar_url") val avatarUrl: String?,
)

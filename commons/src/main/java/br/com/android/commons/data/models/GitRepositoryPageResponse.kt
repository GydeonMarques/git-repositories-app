package br.com.android.commons.data.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class GitRepositoryPageResponse(
    @JsonProperty("total_count") val totalCount: Long?,
    @JsonProperty("items") val items: List<GitRepositoryDataResponse>?
)
package br.com.android.commons.data.models

import br.com.android.commons.util.PageParamsRequest


data class GitRepositoryPullsRequest(
    val username: String,
    val repositoryName: String,
) : PageParamsRequest()

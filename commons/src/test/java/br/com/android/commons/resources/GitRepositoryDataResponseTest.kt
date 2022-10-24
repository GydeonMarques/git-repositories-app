package br.com.android.commons.resources

import br.com.android.commons.data.models.GitRepositoryDataResponse
import br.com.android.commons.data.models.GitRepositoryOwnerResponse

object GitRepositoryDataResponseTest {
    
    val repositoryDataResponseList = arrayListOf<GitRepositoryDataResponse>().apply {
        (0..10).forEach { add(repositoryDataResponse) }
    }

    val repositoryDataResponse = GitRepositoryDataResponse(
        id = 1L,
        name = "Name",
        fullName = "Full name",
        forksCount = 100L,
        description = "Description",
        watchersCount = 500L,
        owner = GitRepositoryOwnerResponse(
            id = 1L,
            login = "User name",
            avatarUrl = "Avatar URL",
        )
    )
}
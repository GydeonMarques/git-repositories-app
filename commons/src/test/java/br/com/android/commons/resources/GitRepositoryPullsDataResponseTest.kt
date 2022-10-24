package br.com.android.commons.resources

import br.com.android.commons.data.models.GitRepositoryPullsDataResponse

object GitRepositoryPullsDataResponseTest {

    val gitRepositoryDataModel = GitRepositoryPullsDataResponse(
        id = 1L,
        body = "Body",
        title = "Title",
        number = 1L,
        pullURL = "Pull URL",
        createdAt = "Created At",
        base = null,
        user = null,
    )

    val GitRepositoryPullsDataResponseList = arrayListOf<GitRepositoryPullsDataResponse>().apply {
        (0..5).forEach { add(gitRepositoryDataModel.copy(id = it.toLong())) }
    }
}
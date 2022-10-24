package br.com.android.commons.resources

import br.com.android.commons.data.models.GitRepositoryPageResponse
import br.com.android.commons.data.models.GitRepositoryPullsDataResponse
import retrofit2.Response

object GitApiResourcesTest {

    val gitRepositoryPageResponse: Response<GitRepositoryPageResponse> = Response.success(
        GitRepositoryPageResponse(
            totalCount = 0,
            items = GitRepositoryDataResponseTest.repositoryDataResponseList
        )
    )

    val gitRepositoryPullsDataResponse: Response<List<GitRepositoryPullsDataResponse>> =
        Response.success(GitRepositoryPullsDataResponseTest.GitRepositoryPullsDataResponseList)
}
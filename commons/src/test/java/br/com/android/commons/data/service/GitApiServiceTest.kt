package br.com.android.commons.data.service

import br.com.android.commons.data.models.GitRepositoryPageResponse
import br.com.android.commons.data.models.GitRepositoryPullsDataResponse
import br.com.android.commons.resources.GitApiResourcesTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

internal class GitApiServiceTest {

    private val service = mockk<GitApiService>()

    @Before
    fun setup() {
        coEvery {
            service.loadAllPublicRepositories(
                page = "1",
                pageSize = "10",
                sort = "create_at",
                language = "language:kotlin"
            )
        } returns GitApiResourcesTest.gitRepositoryPageResponse

        coEvery {
            service.loadAllPullsOfRepository(
                page = "1",
                pageSize = "10",
                sort = "create_at",
                username = "username",
                language = "language:kotlin",
                repositoryName = "repository"
            )
        } returns GitApiResourcesTest.gitRepositoryPullsDataResponse
    }

    @Test
    fun `WHEN fetching the public repositories in the api THEN it should return a SUCCESSFUL response`() {
        runBlocking {
            //given
            val expectResponse = GitApiResourcesTest.gitRepositoryPageResponse

            //when
            val response = service.loadAllPublicRepositories(
                page = "1",
                pageSize = "10",
                sort = "create_at",
                language = "language:kotlin"
            )

            //then
            assertTrue(response.isSuccessful)
            assertEquals(expectResponse.body(), response.body())
        }
    }

    @Test
    fun `WHEN fetching the public repositories in the api THEN it should return the EXPECTED response model`() {
        runBlocking {
            //given

            //when
            val response = service.loadAllPublicRepositories(
                page = "1",
                pageSize = "10",
                sort = "create_at",
                language = "language:kotlin"
            )

            //then
            assertTrue(response.body() is GitRepositoryPageResponse)
        }
    }

    @Test
    fun `WHEN loading all jumps from the repository THEN SHOULD return a SUCCESS response`() {
        runBlocking {
            //given
            val expectResponse = GitApiResourcesTest.gitRepositoryPullsDataResponse

            //when
            val response = service.loadAllPullsOfRepository(
                page = "1",
                pageSize = "10",
                sort = "create_at",
                username = "username",
                language = "language:kotlin",
                repositoryName = "repository"
            )

            //then
            assertTrue(response.isSuccessful)
            assertEquals(expectResponse.body(), response.body())
        }
    }

    @Test
    fun `WHEN loading all hops from repository THEN SHOULD return the EXPECTED response model`() {
        runBlocking {
            //given

            //when
            val response = service.loadAllPullsOfRepository(
                page = "1",
                pageSize = "10",
                sort = "create_at",
                username = "username",
                language = "language:kotlin",
                repositoryName = "repository"
            )

            //then
            assertTrue(response.body() is List<GitRepositoryPullsDataResponse>)
        }
    }

}
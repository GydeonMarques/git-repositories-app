package br.com.android.git.repositories.data.pagging

import androidx.paging.PagingSource
import br.com.android.git.repositories.resources.GitRepositoryPagingPullsDataTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
internal class GitRepositoryPagingPullsDataSourceTest {

    private var pagingDataSource: GitRepositoryPullsPagingDataSource = mockk()

    @Test
    fun `WHEN load is calling THEN it should return a SUCCESS page`() {
        runTest {

            //given
            val loadParams = GitRepositoryPagingPullsDataTest.loadParams
            val expectedResponse = GitRepositoryPagingPullsDataTest.pageResultSuccess

            //when
            coEvery { pagingDataSource.load(loadParams) } returns expectedResponse
            val result = pagingDataSource.load(loadParams)

            //then
            Assert.assertEquals(expectedResponse, result)
            Assert.assertTrue(result is PagingSource.LoadResult.Page)
            if (result is PagingSource.LoadResult.Page) Assert.assertTrue(result.data.isNotEmpty())
        }
    }
}
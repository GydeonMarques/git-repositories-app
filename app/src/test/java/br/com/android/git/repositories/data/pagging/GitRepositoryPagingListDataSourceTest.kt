package br.com.android.git.repositories.data.pagging

import androidx.paging.PagingSource
import br.com.android.git.repositories.resources.GitRepositoryPagingListDataTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
internal class GitRepositoryPagingListDataSourceTest {

    private var pagingDataSource: GitRepositoryPagingDataSource = mockk()

    @Test
    fun `WHEN load is calling THEN it should return a SUCCESS page`() {
        runTest {

            //given
            val loadParams = GitRepositoryPagingListDataTest.loadParams
            val expectedResponse = GitRepositoryPagingListDataTest.pageResultSuccess

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
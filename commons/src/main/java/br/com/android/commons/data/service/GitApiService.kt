package br.com.android.commons.data.service

import br.com.android.commons.data.models.GitRepositoryPageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitApiService {

    @GET("search/repositories")
    suspend fun loadAllPublicRepositories(
        @Query("q") language: String = "language:Java",
        @Query("sort") sort: String = "stars",
        @Query("page") page: String = "1",
    ): Response<GitRepositoryPageResponse>
}
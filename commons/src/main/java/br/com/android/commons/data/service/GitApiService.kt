package br.com.android.commons.data.service

import br.com.android.commons.data.models.GitRepositoryPageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitApiService {

    @GET("search/repositories")
    suspend fun loadAllPublicRepositories(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") page: String,
        @Query("per_page") pageSize: String,
    ): Response<GitRepositoryPageResponse>
}
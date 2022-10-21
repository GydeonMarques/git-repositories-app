package br.com.android.git.repositories.data

import br.com.android.commons.data.models.GitRepositoryPageResponse
import br.com.android.commons.data.models.Result
import br.com.android.commons.data.service.GitApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class GitRepositoryImpl(
    private val service: GitApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : GitRepository {

    override suspend fun loadAllPublicRepositories(): Result<GitRepositoryPageResponse> {
        return try {
            withContext(dispatcher) {

                with(service.loadAllPublicRepositories()) {
                    body()?.let { data ->
                        return@withContext Result.Success(data)
                    }
                    errorBody()?.let { error ->
                        return@withContext Result.Error(message = error.string())
                    }
                }

                return@withContext Result.Error().default()
            }
        } catch (e: Exception) {
            Result.Error(message = e.localizedMessage)
        }
    }
}
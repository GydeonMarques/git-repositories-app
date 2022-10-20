package br.com.android.commons.data.models

import java.net.HttpURLConnection

sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(
        var code: String? = null,
        var message: String? = null,
        val exception: Exception? = null
    ) : Result<Nothing>() {

        fun default(): Error {
            return Error().apply {
                code = HttpURLConnection.HTTP_BAD_REQUEST.toString()
                message = "Houve um erro ao carregar os dados, por favor, tente novamente mais tarde, se o problema persistir, entre em contato com o suporte"
            }
        }
    }

    override fun toString(): String {
        return when (this) {
            is Success<T> -> "Success[data=$data]"
            is Error -> "Error[code=$code, message=$message, exception$exception]"
        }
    }
}
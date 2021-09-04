package com.example.airqualityproject.domain.model

data class DataState<out T>(
    val status: Status,
    val data: T? = null,
    val error: String? = null,
    val loading: Boolean = false,
) {
    companion object {

        fun <T> success(
            data: T
        ): DataState<T> {
            return DataState(
                status = Status.SUCCESS,
                data = data,
            )
        }

        fun <T> error(
            message: String,
        ): DataState<T> {
            return DataState(
                status = Status.ERROR,
                error = message
            )
        }

        fun <T> loading(): DataState<T> =
            DataState(
                status = Status.LOADING,
                loading = true
            )
    }
}

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
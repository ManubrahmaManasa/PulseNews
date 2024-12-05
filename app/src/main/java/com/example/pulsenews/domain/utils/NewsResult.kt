package com.example.pulsenews.domain.utils

sealed interface NewsResult<out D,out E : NewsError> {
    data class Success<out D>(val data:D) : NewsResult<D,Nothing>
    data class Error<out E:NewsError>(val error:E) : NewsResult<Nothing,E>
}
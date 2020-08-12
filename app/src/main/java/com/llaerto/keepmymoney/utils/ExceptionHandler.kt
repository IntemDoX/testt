package com.llaerto.keepmymoney.utils

import retrofit2.HttpException

class TokenNotValidException(message: String? = null) : Exception(message)

private fun Throwable.getHttpErrorCode(): Int {
    return when (this) {
        is HttpException -> this.code()
        else -> 0
    }
}

fun Throwable.handleException(): Throwable {
    when (this.getHttpErrorCode()) {
        401 -> return TokenNotValidException("Token is not valid")
        else -> return this
    }
}
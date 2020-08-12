package com.llaerto.keepmymoney.base

sealed class InternalError {

    data class Throwed(val throwable: Throwable): InternalError()

    data class Custom<T>(val error: T): InternalError()
}
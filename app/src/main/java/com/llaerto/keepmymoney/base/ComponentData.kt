package com.llaerto.keepmymoney.base

sealed class ComponentData<T>(val data: T?) {
    object Empty: ComponentData<Any>(null)

    data class Data<T>(val readyData: T): ComponentData<T>(readyData)

    data class Error<T>(val errorData: T? = null, val error: InternalError): ComponentData<T>(errorData)

    data class Loading<T>(val loadingData: T? = null): ComponentData<T>(loadingData)

    fun isChanged(previous: ComponentData<T>): Boolean {
        return this != previous
    }
}
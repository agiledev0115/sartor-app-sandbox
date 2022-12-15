package com.sartor.data.api

interface OperationCallback<T> {
    fun onSuccess(data: T?)
    fun onError(error: String?)

}
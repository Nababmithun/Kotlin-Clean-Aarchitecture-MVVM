package com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.core.result

sealed class Result<out T> {
    data object Loading : Result<Nothing>()
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val message: String, val throwable: Throwable? = null) : Result<Nothing>()
}

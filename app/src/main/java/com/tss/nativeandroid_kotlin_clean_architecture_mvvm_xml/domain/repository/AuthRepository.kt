package com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.domain.repository

import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.core.result.Result
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.domain.model.User

interface AuthRepository {
    suspend fun register(
        name: String,
        email: String,
        password: String,
        mobile: String?,
        gender: String?
    ): Result<User>

    suspend fun login(email: String, password: String): Result<User>
    suspend fun me(): Result<User>
    suspend fun logout(): Result<Unit>
}

package com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

data class RegisterReq(
    val name: String,
    val email: String,
    val password: String,
    val mobile: String?,
    val gender: String?
)
data class LoginReq(
    val email: String,
    val password: String
)
data class UserDto(
    val id: Int,
    val name: String,
    val email: String,
    val mobile: String?,
    val gender: String?,
    val is_admin: Boolean,
    val avatar_url: String?
)
data class TokenUserRes(
    val message: String,
    val token: String,
    val user: UserDto
)
data class MessageRes(val message: String)

interface ApiService {
    @POST("api/register")
    suspend fun register(@Body body: RegisterReq): TokenUserRes

    @POST("api/login")
    suspend fun login(@Body body: LoginReq): TokenUserRes

    @GET("api/me")
    suspend fun me(@Header("Authorization") bearer: String): UserDto

    @POST("api/logout")
    suspend fun logout(@Header("Authorization") bearer: String): MessageRes
}

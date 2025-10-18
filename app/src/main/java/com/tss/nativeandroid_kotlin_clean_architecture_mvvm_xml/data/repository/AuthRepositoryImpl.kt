package com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.data.repository

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.core.result.Result
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.core.utils.Prefs
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.data.remote.*
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.domain.model.User
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.domain.repository.AuthRepository
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

private fun UserDto.toDomain() = User(
    id = id,
    name = name,
    email = email,
    mobile = mobile,
    gender = gender,
    isAdmin = is_admin
)

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val prefs: Prefs
) : AuthRepository {

    private val moshi by lazy {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    private fun bearer() = "Bearer ${prefs.token ?: ""}"

    override suspend fun register(
        name: String, email: String, password: String, mobile: String?, gender: String?
    ): Result<User> = try {
        Log.d("AuthRepo", "Register request: $email")
        val res = api.register(RegisterReq(name, email, password, mobile, gender))
        prefs.token = res.token
        Log.d("AuthRepo", "Register success: token=${res.token.take(6)}..., user=${res.user.email}")
        Result.Success(res.user.toDomain())
    } catch (t: Throwable) {
        Result.Error(parseError("Register failed", t), t)
    }

    override suspend fun login(email: String, password: String): Result<User> = try {
        Log.d("AuthRepo", "Login request: $email")
        val res = api.login(LoginReq(email, password))
        prefs.token = res.token
        Log.d("AuthRepo", "Login success: token=${res.token.take(6)}..., user=${res.user.email}")
        Result.Success(res.user.toDomain())
    } catch (t: Throwable) {
        Result.Error(parseError("Login failed", t), t)
    }

    override suspend fun me(): Result<User> = try {
        Log.d("AuthRepo", "Me request with token=${prefs.token?.take(6)}...")
        val user = api.me(bearer())
        Result.Success(user.toDomain())
    } catch (t: Throwable) {
        Result.Error(parseError("Me failed", t), t)
    }

    override suspend fun logout(): Result<Unit> = try {
        Log.d("AuthRepo", "Logout request")
        api.logout(bearer())
        prefs.token = null
        Result.Success(Unit)
    } catch (t: Throwable) {
        Result.Error(parseError("Logout failed", t), t)
    }

    private fun parseError(prefix: String, t: Throwable): String {
        return when (t) {
            is HttpException -> {
                val body = t.response()?.errorBody()?.string()
                val msg = try {
                    val adapter = moshi.adapter(MessageRes::class.java)
                    adapter.fromJson(body ?: "")?.message
                } catch (_: Throwable) { null }
                val finalMsg = msg ?: "HTTP ${t.code()}: ${t.message()}"
                Log.e("AuthRepo", "$prefix -> $finalMsg, raw=$body", t)
                "$prefix: $finalMsg"
            }
            else -> {
                Log.e("AuthRepo", "$prefix -> ${t.message}", t)
                "$prefix: ${t.message ?: "Unknown error"}"
            }
        }
    }
}

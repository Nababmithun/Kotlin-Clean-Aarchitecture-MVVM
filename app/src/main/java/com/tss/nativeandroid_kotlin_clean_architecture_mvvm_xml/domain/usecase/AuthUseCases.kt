package com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.domain.usecase

import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repo: AuthRepository) {
    suspend operator fun invoke(name:String,email:String,password:String,mobile:String?,gender:String?) =
        repo.register(name,email,password,mobile,gender)
}
class LoginUseCase @Inject constructor(private val repo: AuthRepository) {
    suspend operator fun invoke(email:String,password:String) = repo.login(email,password)
}
class MeUseCase @Inject constructor(private val repo: AuthRepository) {
    suspend operator fun invoke() = repo.me()
}
class LogoutUseCase @Inject constructor(private val repo: AuthRepository) {
    suspend operator fun invoke() = repo.logout()
}

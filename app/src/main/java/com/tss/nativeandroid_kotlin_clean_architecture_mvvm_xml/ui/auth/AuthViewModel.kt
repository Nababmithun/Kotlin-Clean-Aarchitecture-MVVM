package com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.core.result.Result
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.domain.model.User
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.domain.usecase.LoginUseCase
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.domain.usecase.LogoutUseCase
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.domain.usecase.MeUseCase
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUC: LoginUseCase,
    private val registerUC: RegisterUseCase,
    private val meUC: MeUseCase,
    private val logoutUC: LogoutUseCase
) : ViewModel() {

    private val _auth = MutableStateFlow<Result<User>>(Result.Loading)
    val auth: StateFlow<Result<User>> = _auth

    fun login(email: String, password: String) = viewModelScope.launch {
        _auth.value = Result.Loading
        _auth.value = loginUC(email, password)
    }

    fun register(name: String, email: String, password: String, mobile: String?, gender: String?) = viewModelScope.launch {
        _auth.value = Result.Loading
        _auth.value = registerUC(name, email, password, mobile, gender)
    }

    fun me() = viewModelScope.launch {
        _auth.value = Result.Loading
        _auth.value = meUC()
    }

    fun logout() = viewModelScope.launch { logoutUC() }
}

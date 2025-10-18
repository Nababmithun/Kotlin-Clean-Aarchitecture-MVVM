package com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.domain.model

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val mobile: String?,
    val gender: String?,
    val isAdmin: Boolean
)

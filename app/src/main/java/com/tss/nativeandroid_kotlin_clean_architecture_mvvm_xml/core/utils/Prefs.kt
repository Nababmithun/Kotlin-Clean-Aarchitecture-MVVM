package com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.core.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Prefs @Inject constructor(
    @ApplicationContext context: Context
) {
    private val sp = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    // Access Token (login persistence)
    var token: String?
        get() = sp.getString("token", null)
        set(value) {
            sp.edit().putString("token", value).apply()
        }

    // Language preference (for LanguageManager)
    var language: String?
        get() = sp.getString("language", null)
        set(value) {
            sp.edit().putString("language", value).apply()
        }

    // Helper: clear all data on logout
    fun clearAll() {
        sp.edit().clear().apply()
    }

    // Helper: check if user is logged in
    fun isLoggedIn(): Boolean = !token.isNullOrBlank()
}

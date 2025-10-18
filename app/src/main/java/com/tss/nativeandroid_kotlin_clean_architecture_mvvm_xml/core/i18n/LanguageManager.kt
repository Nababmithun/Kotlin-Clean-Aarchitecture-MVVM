package com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.core.i18n

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

object LanguageManager {

    private const val DEFAULT_LANG = "en"

    fun setLanguage(langCode: String) {
        val locales = LocaleListCompat.forLanguageTags(langCode)
        AppCompatDelegate.setApplicationLocales(locales)
    }

    fun currentLanguage(): String {
        val tags = AppCompatDelegate.getApplicationLocales().toLanguageTags()
        return if (tags.isNullOrBlank()) DEFAULT_LANG else tags
    }
}

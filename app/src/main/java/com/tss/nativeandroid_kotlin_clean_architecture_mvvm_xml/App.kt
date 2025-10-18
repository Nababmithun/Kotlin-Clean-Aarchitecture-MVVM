package com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml

import android.app.Application
import android.content.Context
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.core.utils.LocaleHelper
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.core.utils.Prefs
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var prefs: Prefs

    override fun attachBaseContext(base: Context?) {
        // "en"
        val lang = base?.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            ?.getString("language", "en") ?: "en"
        super.attachBaseContext(base?.let { LocaleHelper.setLocale(it, lang) })
    }
}

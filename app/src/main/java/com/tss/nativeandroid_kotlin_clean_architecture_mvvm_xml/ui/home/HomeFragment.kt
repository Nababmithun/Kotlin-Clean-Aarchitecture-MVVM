package com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.ui.home

import android.os.Bundle
import android.view.*
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.core.ui.BaseFragment
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.databinding.FragmentHomeBinding
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.core.i18n.LanguageManager
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.core.utils.Prefs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    @Inject lateinit var prefs: Prefs

    override fun inflate(i: LayoutInflater, c: ViewGroup?, a: Boolean) =
        FragmentHomeBinding.inflate(i, c, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        // (bn  switch ON, OFF)
        val current = LanguageManager.currentLanguage()
        switchLang.isChecked = current.startsWith("bn", ignoreCase = true)

        // activity recreate
        switchLang.setOnCheckedChangeListener { _, isChecked ->
            val lang = if (isChecked) "bn" else "en"
            if (lang != LanguageManager.currentLanguage()) {
                prefs.language = lang
                LanguageManager.setLanguage(lang)
                // UI
                requireActivity().recreate()
            }
        }
    }
}

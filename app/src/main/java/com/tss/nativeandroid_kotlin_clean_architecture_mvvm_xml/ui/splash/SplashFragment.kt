package com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.R
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.core.utils.Prefs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment() {

    @Inject
    lateinit var prefs: Prefs

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            delay(1000) // ⏱️ optional splash delay

            val navController = findNavController()

            if (prefs.isLoggedIn()) {
              //MainFragment-
                navController.navigate(R.id.action_splash_to_main)
            } else {
                // LoginFragment
                navController.navigate(R.id.action_splash_to_login)
            }
        }
    }
}

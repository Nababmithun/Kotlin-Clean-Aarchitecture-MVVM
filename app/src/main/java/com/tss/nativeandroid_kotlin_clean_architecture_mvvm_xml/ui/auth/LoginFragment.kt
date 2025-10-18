package com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.R
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.core.result.Result
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.core.ui.BaseFragment
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val vm: AuthViewModel by viewModels()

    override fun inflate(i: LayoutInflater, c: ViewGroup?, a: Boolean) =
        FragmentLoginBinding.inflate(i, c, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val pass = etPassword.text.toString().trim()
            vm.login(email, pass)
        }
        tvGoRegister.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.auth.collect { res ->
                    when (res) {
                        is Result.Loading -> { /* show progress */ }
                        is Result.Error -> Toast.makeText(requireContext(), res.message, Toast.LENGTH_SHORT).show()
                        is Result.Success -> {
                            Toast.makeText(requireContext(), getString(R.string.logged_in), Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_login_to_home)
                        }
                    }
                }
            }
        }
    }
}

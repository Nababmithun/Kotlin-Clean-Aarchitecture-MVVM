package com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.R
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.core.result.Result
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.core.ui.BaseFragment
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    private val vm: AuthViewModel by viewModels()

    override fun inflate(i: LayoutInflater, c: ViewGroup?, a: Boolean) =
        FragmentRegisterBinding.inflate(i, c, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {

        val genderOptions = listOf("Male", "Female", "Other")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, genderOptions)
        spGender.setAdapter(adapter)

        btnRegister.setOnClickListener {
            vm.register(
                etName.text.toString().trim(),
                etEmail.text.toString().trim(),
                etPassword.text.toString().trim(),
                etMobile.text.toString().trim().ifEmpty { null },
                spGender.text.toString().trim().ifEmpty { null }
            )
        }

        tvGoLogin.setOnClickListener { findNavController().navigateUp() }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.auth.collect { res ->
                    when (res) {
                        is Result.Loading -> { /* show progress */ }
                        is Result.Error -> Toast.makeText(requireContext(), res.message, Toast.LENGTH_SHORT).show()
                        is Result.Success -> {
                            Toast.makeText(requireContext(), getString(R.string.registered), Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_register_to_home)
                        }
                    }
                }
            }
        }
    }
}

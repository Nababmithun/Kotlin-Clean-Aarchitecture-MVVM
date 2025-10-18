package com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.ui.profile

import android.view.*
import androidx.fragment.app.viewModels
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.core.ui.BaseFragment
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.databinding.FragmentProfileBinding
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.ui.auth.AuthViewModel

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    private val vm: AuthViewModel by viewModels({ requireActivity() })
    override fun inflate(i: LayoutInflater, c: ViewGroup?, a: Boolean) = FragmentProfileBinding.inflate(i, c, false)
}

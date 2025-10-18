package com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.core.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


// Lightweight base if you don't use a 3rd-party delegate, keeping simple manual binding in fragments instead.
abstract class BaseFragment<VB: ViewBinding> : Fragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    abstract fun inflate(inflater: LayoutInflater, container: ViewGroup?, attach: Boolean): VB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

package com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.ui.history

import android.view.*
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.core.ui.BaseFragment
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.databinding.FragmentHistoryBinding

class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {
    override fun inflate(i: LayoutInflater, c: ViewGroup?, a: Boolean) = FragmentHistoryBinding.inflate(i, c, false)
}

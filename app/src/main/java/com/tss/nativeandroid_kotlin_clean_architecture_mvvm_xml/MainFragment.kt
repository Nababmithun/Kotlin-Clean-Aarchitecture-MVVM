package com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Safe NavHost setup with try-catch
        val navHostFragment = childFragmentManager.findFragmentById(R.id.main_nav_host) as? NavHostFragment
        if (navHostFragment != null) {
            val navController = navHostFragment.navController
            binding.bottomNav.setupWithNavController(navController)
        } else {
            AlertDialog.Builder(requireContext())
                .setTitle("Navigation Error")
                .setMessage("Navigation host not found. Please check fragment_main.xml.")
                .setPositiveButton("OK", null)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

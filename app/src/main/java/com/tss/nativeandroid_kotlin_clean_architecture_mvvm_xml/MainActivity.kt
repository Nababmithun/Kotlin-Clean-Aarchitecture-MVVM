package com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.core.connectivity.NetworkMonitor
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    private var networkDialog: AlertDialog? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Navigation setup
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as? NavHostFragment
        navHostFragment?.navController ?: error("❌ NavHostFragment not found. Check nav_host ID in XML.")

        // Observe network state
        observeNetworkState()
    }

    /**
     * Network connectivity observer
     */
    private fun observeNetworkState() {
        lifecycleScope.launch {
            networkMonitor.isConnected.collectLatest { connected ->
                if (!connected) showNetworkDialog() else hideNetworkDialog()
            }
        }
    }

    /**
     * Show "No Internet" dialog with Retry button
     */
    private fun showNetworkDialog() {
        if (networkDialog?.isShowing == true) return

        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_network_state, null)
        dialogView.findViewById<Button>(R.id.btnRetry).setOnClickListener {
            hideNetworkDialog()
            // Retry
            observeNetworkState()
        }

        networkDialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        networkDialog?.show()
    }

    /**
     * Hide network dialog safely
     */
    private fun hideNetworkDialog() {
        networkDialog?.dismiss()
        networkDialog = null
    }

    override fun onDestroy() {
        super.onDestroy()
        hideNetworkDialog()
    }
}

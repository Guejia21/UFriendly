package edu.unicauca.aplimovil.ufriendly

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import edu.unicauca.aplimovil.ufriendly.data.db.AppDatabase
import edu.unicauca.aplimovil.ufriendly.notifications.NotificationHelper
import edu.unicauca.aplimovil.ufriendly.ui.nav.AppNavHost
import edu.unicauca.aplimovil.ufriendly.ui.theme.UFriendlyTheme
import kotlin.getValue
class MainActivity : ComponentActivity() {
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { /* you can show a rationale here if denied */ }
    val database by lazy {
        AppDatabase.getDatabase(applicationContext)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1. Create the notification channel
        NotificationHelper.createChannel(this)
        // 2. Request permission on Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
        enableEdgeToEdge()
        setContent {
            UFriendlyTheme {
                //TaskScreen(tasks = tasks, onAddClick = {})
                //val state = DashboardState("Jhoan Chacon",1,2,2)
                //MainScreen(state, listOf(subject, subject2), onAddClick = {}, onViewAllClick = {})
                AppNavHost(database = database)
            }
        }
    }
}
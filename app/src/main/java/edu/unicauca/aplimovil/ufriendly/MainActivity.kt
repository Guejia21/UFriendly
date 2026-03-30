package edu.unicauca.aplimovil.ufriendly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import edu.unicauca.aplimovil.ufriendly.data.db.AppDatabase
import edu.unicauca.aplimovil.ufriendly.ui.nav.AppNavHost
import edu.unicauca.aplimovil.ufriendly.ui.theme.UFriendlyTheme
import kotlin.getValue
class MainActivity : ComponentActivity() {
    val database by lazy {
        AppDatabase.getDatabase(applicationContext)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
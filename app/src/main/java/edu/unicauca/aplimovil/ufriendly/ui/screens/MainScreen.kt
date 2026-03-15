package edu.unicauca.aplimovil.ufriendly.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.unicauca.aplimovil.ufriendly.data.DashboardState
import edu.unicauca.aplimovil.ufriendly.data.Subject
import edu.unicauca.aplimovil.ufriendly.ui.components.BottomBar
import edu.unicauca.aplimovil.ufriendly.ui.components.SubjectDashboard
import edu.unicauca.aplimovil.ufriendly.ui.components.WelcomeDashboard
import edu.unicauca.aplimovil.ufriendly.ui.nav.ScreenName
import edu.unicauca.aplimovil.ufriendly.ui.theme.UFriendlyTheme

@Composable
fun MainScreen(
    state: DashboardState,
    subjects: List<Subject>,
    navController: NavHostController
) {
    GenericScreen(navController=navController)
    {
        Column {
            WelcomeDashboard(
                state = state,
                onViewAllClick = { navController.navigate(ScreenName.TaskScreen.name) }
            )
            SubjectDashboard(
                subjects = subjects,
                onViewAllClick = { navController.navigate(ScreenName.SubjectScreen.name) }
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    UFriendlyTheme() {
        MainScreen(
            state = state,
            subjects = subjects,
            navController = rememberNavController()
        )
    }
}
val state = DashboardState(
    userName = "Jhoan Chacon",
    pendingCount = 5,
    doneCount = 10,
    expiredCount = 2
)
val subjects = listOf(
    Subject("Matemáticas", listOf("Lunes 8-11", "Miércoles 8-11"), "Juan Pérez", 80, 2.9, Color(0xFFE8D08A)),
    Subject("Física", listOf("Martes 11-13", "Jueves 12-14"), "María Gómez", 75, 3.2, Color(0xFFD0C3E6)),
    Subject("Química", listOf("Lunes 13-16", "Miércoles 13-16"), "Carlos Rodríguez", 60, 2.5, Color(0xFFE8D08A)),
    Subject("Historia", listOf("Martes 16-19", "Jueves 16-19"), "Ana López", 90, 4.0, Color(0xFFE8D08A)),
    Subject("Biología", listOf("Lunes 19-22", "Miércoles 19-22"), "Pedro Martínez", 70, 3.8, Color(0xFFD0C3E6)),
    Subject("Literatura", listOf("Martes 22-25", "Jueves 22-25"), "Laura Sánchez", 85, 3.7, Color(0xFFE8D08A))
)
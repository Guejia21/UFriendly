package edu.unicauca.aplimovil.ufriendly.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.unicauca.aplimovil.ufriendly.data.DashboardState
import edu.unicauca.aplimovil.ufriendly.data.Subject
import edu.unicauca.aplimovil.ufriendly.data.Task
import edu.unicauca.aplimovil.ufriendly.ui.screens.MainScreen
import edu.unicauca.aplimovil.ufriendly.ui.screens.SubjectScreen
import edu.unicauca.aplimovil.ufriendly.ui.screens.TaskScreen
import edu.unicauca.aplimovil.ufriendly.ui.screens.subjects
import java.time.LocalDate
import java.time.format.DateTimeFormatter

enum class ScreenName{
    Home,
    SubjectScreen,
    TaskScreen
}
val subject = Subject("Cálculo I", listOf("Lunes 8-11"), "Juan Pérez", 80, 2.9, Color(0xFFE8D08A))
val subject2 = Subject("Programación Avanzada", listOf("Martes 10-12"), "Ana García", 75, 3.5, Color(0xFF90CAF9))
val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
val tasks = listOf(
    Task("Preparar presentación de cálculo", "Desc", today, false, subject),
    Task("Estudiar ondas", "Desc", today, false, subject),
    Task("Proyecto de programación", "Desc",    "2023-10-28", false, subject2),
    Task("Proyecto de automatización", "Desc", "2023-10-28", false, subject2)
)
val state = DashboardState("Jhoan Chacon",1,2,2)
@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = ScreenName.Home.name,
    modifier: Modifier = Modifier
) {
    NavHost(modifier = modifier, navController = navController, startDestination = startDestination) {

        composable(route = ScreenName.Home.name) {
            MainScreen(
                state,
                listOf(subject, subject2),
                onAddClick = {},
                onViewAllClick = {},
                navController = navController  // ← pasar
            )
        }
        composable(route = ScreenName.SubjectScreen.name) {
            SubjectScreen(
                subjects = subjects,
                onAddClick = {},
                navController = navController  // ← pasar
            )
        }
        composable(route = ScreenName.TaskScreen.name) {
            TaskScreen(
                tasks = tasks,
                onAddClick = {},
                navController = navController  // ← pasar
            )
        }
    }
}
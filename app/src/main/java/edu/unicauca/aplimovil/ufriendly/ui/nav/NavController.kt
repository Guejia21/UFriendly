package edu.unicauca.aplimovil.ufriendly.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.unicauca.aplimovil.ufriendly.data.DashboardState
import edu.unicauca.aplimovil.ufriendly.data.Grade
import edu.unicauca.aplimovil.ufriendly.data.Subject
import edu.unicauca.aplimovil.ufriendly.data.Task
import edu.unicauca.aplimovil.ufriendly.ui.screens.AddGradeScreen
import edu.unicauca.aplimovil.ufriendly.ui.screens.AddSubjectScreen
import edu.unicauca.aplimovil.ufriendly.ui.screens.AddTaskScreen
import edu.unicauca.aplimovil.ufriendly.ui.screens.GradesScreen
import edu.unicauca.aplimovil.ufriendly.ui.screens.MainScreen
import edu.unicauca.aplimovil.ufriendly.ui.screens.SubjectScreen
import edu.unicauca.aplimovil.ufriendly.ui.screens.TaskScreen
import edu.unicauca.aplimovil.ufriendly.ui.screens.subjects
import java.time.LocalDate
import java.time.format.DateTimeFormatter

enum class ScreenName{
    Home,
    SubjectScreen,
    TaskScreen,
    GradesScreen,
    AddTaskScreen,
    AddSubjectScreen,
    AddGradeScreen
}
val subject = Subject("Cálculo I", listOf("Lunes 8-11"), "Juan Pérez", 80, 2.9, Color(0xFFE8D08A), listOf(Grade("1er parcial", 4.2, 0.35, "2023-10-28"), Grade("2do parcial", 4.5, 0.35, "2023-10-28")))
val subject2 = Subject("Programación Avanzada", listOf("Martes 10-12"), "Ana García", 75, 3.5, Color(0xFF90CAF9), listOf(Grade("1er parcial", 3.5, 0.35, "2023-10-28")))
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
                navController = navController
            )
        }
        composable(route = ScreenName.SubjectScreen.name) {
            SubjectScreen(
                subjects = subjects,
                navController = navController
            )
        }
        composable(route = ScreenName.TaskScreen.name) {
            TaskScreen(
                tasks = tasks,
                navController = navController
            )
        }
        composable(route = ScreenName.GradesScreen.name) {
            GradesScreen(
                subjects = listOf(subject, subject2),
                navController = navController
            )
        }
        composable(route = ScreenName.AddTaskScreen.name ) {
            AddTaskScreen(navController, subjects)
        }
        composable(route = ScreenName.AddSubjectScreen.name) {
            AddSubjectScreen(navController)
        }
        composable(route = ScreenName.AddGradeScreen.name) {
            AddGradeScreen(navController)
        }
    }
}
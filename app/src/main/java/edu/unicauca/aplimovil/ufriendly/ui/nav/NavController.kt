package edu.unicauca.aplimovil.ufriendly.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.unicauca.aplimovil.ufriendly.data.DashboardState
import edu.unicauca.aplimovil.ufriendly.data.db.AppDatabase
import edu.unicauca.aplimovil.ufriendly.data.repository.GradeRepository
import edu.unicauca.aplimovil.ufriendly.data.repository.SubjectRepository
import edu.unicauca.aplimovil.ufriendly.data.repository.TaskRepository
import edu.unicauca.aplimovil.ufriendly.ui.screens.AddGradeScreen
import edu.unicauca.aplimovil.ufriendly.ui.screens.AddSubjectScreen
import edu.unicauca.aplimovil.ufriendly.ui.screens.AddTaskScreen
import edu.unicauca.aplimovil.ufriendly.ui.screens.GradesScreen
import edu.unicauca.aplimovil.ufriendly.ui.screens.MainScreen
import edu.unicauca.aplimovil.ufriendly.ui.screens.SubjectDetailScreen
import edu.unicauca.aplimovil.ufriendly.ui.screens.SubjectScreen
import edu.unicauca.aplimovil.ufriendly.ui.screens.TaskDetailScreen
import edu.unicauca.aplimovil.ufriendly.ui.screens.TaskScreen
import edu.unicauca.aplimovil.ufriendly.ui.viewModels.GradeViewModel
import edu.unicauca.aplimovil.ufriendly.ui.viewModels.LibraryViewModel
import edu.unicauca.aplimovil.ufriendly.ui.viewModels.SubjectViewModel
import edu.unicauca.aplimovil.ufriendly.ui.viewModels.TaskViewModel
import kotlin.getValue

enum class ScreenName{
    Home,
    SubjectScreen,
    TaskScreen,
    GradesScreen,
    AddTaskScreen,
    AddSubjectScreen,
    AddGradeScreen,
    TaskDetailScreen,
    SubjectDetailScreen,
}

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = ScreenName.Home.name,
    database: AppDatabase,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val taskRepository by lazy { TaskRepository(database.taskDao(), context.applicationContext) }
    val taskViewModel: TaskViewModel = viewModel(factory= TaskViewModel.provideFactory(taskRepository))
    val taskUiState by taskViewModel.uiState.collectAsState()
    val subjectRepository by lazy { SubjectRepository(database.subjectDao(), database.classScheduleDao()) }
    val subjectViewModel: SubjectViewModel = viewModel(factory= SubjectViewModel.provideFactory(subjectRepository))
    val subjectUiState by subjectViewModel.uiState.collectAsState()
    val gradeRepository by lazy { GradeRepository(database.gradeDao()) }
    val gradeViewModel: GradeViewModel = viewModel(factory= GradeViewModel.provideFactory(gradeRepository))
    val gradeUiState by gradeViewModel.uiState.collectAsState()
    NavHost(modifier = modifier, navController = navController, startDestination = startDestination) {

        composable(route = ScreenName.Home.name) {
            MainScreen(
                DashboardState(
                    pendingCount = taskUiState.pendingTasks,
                    doneCount = taskUiState.doneTasks,
                    expiredCount = taskUiState.expiredTasks
                ),
                subjects=subjectUiState.subjectList,
                navController = navController
            )
        }
        composable(route = ScreenName.SubjectScreen.name) {
            SubjectScreen(
                subjects = subjectUiState.subjectList,
                navController = navController
            )
        }
        composable(route = ScreenName.TaskScreen.name) {
            TaskScreen(
                taskList = taskUiState.taskList,
                navController = navController,
                onCheckedChange = taskViewModel::changeStatus,
                onDelete = taskViewModel::deleteTask
            )
        }
        composable(route = ScreenName.GradesScreen.name) {
            GradesScreen(
                grades = gradeUiState.gradeList,
                navController = navController
            )
        }
        composable(route = ScreenName.AddTaskScreen.name ) {
            AddTaskScreen(
                navController = navController,
                subjects = subjectUiState.subjectList,
                addTaskItem = taskViewModel::addTask
            )
        }
        composable(route = ScreenName.AddSubjectScreen.name) {
            AddSubjectScreen(
                navController,
                addSubjectItem = subjectViewModel::insertCompleteSubject
            )
        }
        composable(route = ScreenName.AddGradeScreen.name) {
            AddGradeScreen(
                navController = navController,
                subjects = subjectUiState.subjectList,
                addGradeItem = gradeViewModel::addGrade
            )
        }
        composable(
            route = ScreenName.TaskDetailScreen.name + "/{taskId}",
            arguments = listOf(
                navArgument("taskId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId")
            val task = taskUiState.taskList.find { it.task.id == taskId }
            if (task != null){
                TaskDetailScreen(
                    task = task,
                    navController = navController,
                    onMarkAsDone = {check->
                        (taskViewModel::changeStatus)(task.task, check) //Change the status to done
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("success_message", "Task done")
                        navController.popBackStack() //Regresa a la pantalla de tareas
                                   },
                    onDelete = { (taskViewModel::deleteTask)(task.task)
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("success_message", "Task deleted successfully")
                        navController.popBackStack()
                    }
                )
            }

        }
        composable(
            route = ScreenName.SubjectDetailScreen.name + "/{subjectId}",
            arguments = listOf(
                navArgument("subjectId") {
                    type = NavType.IntType
                }
            )
        ){ backStackEntry ->
            val subjectId = backStackEntry.arguments?.getInt("subjectId")
            val subject = subjectUiState.subjectList.find { it.subject.id == subjectId }
            if (subject != null){
                val libraryViewModel: LibraryViewModel = viewModel(factory = LibraryViewModel.provideFactory(subject.subject.name))
                SubjectDetailScreen(
                    subject = subject.subject,
                    libraryUiState = libraryViewModel.libraryUiState,
                    navController = navController
                )
            }
        }
    }
}
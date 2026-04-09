// TaskScreen.kt - Pantalla principal actualizada
package edu.unicauca.aplimovil.ufriendly.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import edu.unicauca.aplimovil.ufriendly.R
import edu.unicauca.aplimovil.ufriendly.data.entity.Task
import edu.unicauca.aplimovil.ufriendly.data.relation.TaskWithSubject
import edu.unicauca.aplimovil.ufriendly.ui.components.*
import edu.unicauca.aplimovil.ufriendly.ui.nav.ScreenName
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

@Composable
fun TaskScreen(
    taskList: List<TaskWithSubject>,
    navController: NavHostController,
    onCheckedChange: (Task, Boolean) -> Unit = { _, _ -> },
    onDelete: (Task) -> Unit = {},
) {
    //Variable para saber que botón está seleccionado
    var filterSelected by remember { mutableStateOf("Pending") }

    // Filtrar tareas según selección
    val filteredTasks = when (filterSelected) {
        "Pending"  -> taskList.filter { !it.task.isDone }
        "Done"     -> taskList.filter { it.task.isDone }
        "Expired"  -> taskList.filter { isExpired(it.task.dueDate) && !it.task.isDone }
        else       -> taskList
    }
    // Separar tareas de hoy y próximas
    val todayTasks = filteredTasks.filter { isToday(it.task.dueDate)}
    val upcomingTasks = filteredTasks.filter { !isToday(it.task.dueDate) && !isExpired(it.task.dueDate)}
    val lateTasks = filteredTasks.filter { isExpired(it.task.dueDate)}

    val taskUpdatedMsg = stringResource(R.string.task_updated_label)
    val taskDeletedMsg = stringResource(R.string.task_deleted_label)

    GenericScreen(
        navController = navController,
        topBar = { TopBar(stringResource(R.string.task_label)) }
    ) {
        Column {
            // Botones de filtro
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    text = stringResource(R.string.pending_task),
                    isSelected = filterSelected == "Pending",
                    onClick = { filterSelected = "Pending" }
                )
                Button(
                    text = stringResource(R.string.all_label),
                    isSelected = filterSelected == "All",
                    onClick = { filterSelected = "All" }
                )
                Button(
                    text = stringResource(R.string.done_label_task),
                    isSelected = filterSelected == "Done",
                    onClick = { filterSelected = "Done" }
                )
                Button(
                    text = stringResource(R.string.expired_task_label),
                    isSelected = filterSelected == "Expired",
                    onClick = { filterSelected = "Expired" }
                )
            }

            // Lista de tareas con secciones
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                // Sección "Hoy"
                if (todayTasks.isNotEmpty()) {
                    item {
                        TaskSection(
                            title = stringResource(R.string.today_label),
                            tasks = todayTasks,
                            onCheckedChange = { task, checked ->
                                onCheckedChange(task, checked)
                                navController.currentBackStackEntry
                                    ?.savedStateHandle
                                    ?.set("success_message", taskUpdatedMsg)
                            },
                            onDelete = { task ->
                                onDelete(task)
                                navController.currentBackStackEntry
                                    ?.savedStateHandle
                                    ?.set("success_message", taskDeletedMsg)

                            },
                            // La tarea "task" es añadidad en el onItemClick dentro de TaskSection
                            onItemClick = { task ->
                                navController.navigate("${ScreenName.TaskDetailScreen.name}/${task.id}")
                            }
                        )
                    }
                }

                // Sección "Próxima" en caso de que sean tareas pendientes
                if (upcomingTasks.isNotEmpty()) {
                    item {
                        TaskSection(
                            title = stringResource(R.string.upcoming_label),
                            tasks = upcomingTasks,
                            onCheckedChange = { task, checked ->
                                onCheckedChange(task, checked)
                                navController.currentBackStackEntry
                                    ?.savedStateHandle
                                    ?.set("success_message", taskUpdatedMsg)
                            },
                            onDelete = { task ->
                                onDelete(task)
                                navController.currentBackStackEntry
                                    ?.savedStateHandle
                                    ?.set("success_message", taskDeletedMsg)

                            },
                            onItemClick = { task ->
                                navController.navigate("${ScreenName.TaskDetailScreen.name}/${task.id}")
                            }
                        )
                    }
                }
                // Sección "Tarde" en caso de que sean tareas expiradas
                if (lateTasks.isNotEmpty()) {
                    item {
                        TaskSection(
                            title = stringResource(R.string.expired_label),
                            tasks = lateTasks,
                            onCheckedChange = { task, checked ->
                                onCheckedChange(task, checked)
                                navController.currentBackStackEntry
                                    ?.savedStateHandle
                                    ?.set("success_message", taskUpdatedMsg)
                            },
                            onDelete = { task ->
                                onDelete(task)
                                navController.currentBackStackEntry
                                    ?.savedStateHandle
                                    ?.set("success_message", taskDeletedMsg)

                            },
                            onItemClick = { task ->
                                navController.navigate("${ScreenName.TaskDetailScreen.name}/${task.id}")
                            }
                        )
                    }
                }
                // Mensaje vacío
                if (filteredTasks.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            contentAlignment = androidx.compose.ui.Alignment.Center
                        ) {
                            Text(
                                text = stringResource(
                                    R.string.no_tasks_label,
                                    filterSelected.lowercase()
                                ),
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

// Funciones para revisar el vencimiento de las tareas
fun isToday(date: Date?): Boolean {
    if (date == null) return false
    // El DatePicker de Material3 guarda la fecha en UTC (media noche).
    // Para comparar correctamente, extraemos la fecha usando la zona horaria UTC.
    val taskDate = date.toInstant().atZone(ZoneId.of("UTC")).toLocalDate()
    return taskDate == LocalDate.now()
}

fun isExpired(date: Date?): Boolean {
    if (date == null) return false
    val taskDate = date.toInstant().atZone(ZoneId.of("UTC")).toLocalDate()
    return taskDate.isBefore(LocalDate.now())
}

// TaskScreen.kt - Pantalla principal actualizada
package edu.unicauca.aplimovil.ufriendly.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.unicauca.aplimovil.ufriendly.R
import edu.unicauca.aplimovil.ufriendly.data.entity.Task
import edu.unicauca.aplimovil.ufriendly.data.repository.TaskRepository
import edu.unicauca.aplimovil.ufriendly.ui.components.*
import edu.unicauca.aplimovil.ufriendly.ui.theme.UFriendlyTheme
import edu.unicauca.aplimovil.ufriendly.ui.viewModels.TaskViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun TaskScreen(
    taskList: List<Task>,
    navController: NavHostController,
    onCheckedChange: (Task, Boolean) -> Unit = { _, _ -> },
) {
    //Variable para saber que botón está seleccionado
    var filterSelected by remember { mutableStateOf("Pending") }

    // Filtrar tareas según selección
    val filteredTasks = when (filterSelected) {
        "Pending"  -> taskList.filter { !it.isDone }
        "Done"     -> taskList.filter { it.isDone }
        "Expired"  -> taskList.filter { isExpired(it.dueDate) && !it.isDone }
        else       -> taskList
    }
    // Separar tareas de hoy y próximas
    val todayTasks = filteredTasks.filter { isToday(it.dueDate)}
    //TODO Revisar la conversión de las fechas porque no las está reconociendo
    //Ejemplo: Una tarea de hace 10 días no la toma como expirada
    val upcomingTasks = filteredTasks.filter { !isToday(it.dueDate) && !isExpired(it.dueDate)}
    val lateTasks = filteredTasks.filter { isExpired(it.dueDate)}

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
private fun isToday(dateStr: String): Boolean {
    return try {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val taskDate = LocalDate.parse(dateStr, formatter)
        taskDate == LocalDate.now()
    } catch (e: Exception) { false }
}

private fun isExpired(dateStr: String): Boolean {
    return try {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val taskDate = LocalDate.parse(dateStr, formatter)
        taskDate.isBefore(LocalDate.now())
    } catch (e: Exception) { false }
}
/*
@Preview(showBackground = true)
@Composable
fun TaskScreenPreview() {
    val subject = Subject("Cálculo I", listOf("Lunes 8-11"), "Juan Pérez", 80, 2.9, Color(0xFFE8D08A), null)
    val subject2 = Subject("Programación Avanzada", listOf("Martes 10-12"), "Ana García", 75, 3.5, Color(0xFF90CAF9), null)
    val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    val tasks = listOf(
        Task("Preparar presentación de cálculo", "Desc", today, false, subject),
        Task("Estudiar ondas", "Desc", today, false, subject),
        Task("Proyecto de programación", "Desc", "2026-04-28", false, subject2),
        Task("Proyecto de automatización", "Desc", "2023-10-28", false, subject2)
    )
    UFriendlyTheme {
        TaskScreen(tasks = tasks, navController = rememberNavController())
    }
}*/
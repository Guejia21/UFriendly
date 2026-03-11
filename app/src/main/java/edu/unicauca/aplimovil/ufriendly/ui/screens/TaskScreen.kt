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
import edu.unicauca.aplimovil.ufriendly.R
import edu.unicauca.aplimovil.ufriendly.data.Subject
import edu.unicauca.aplimovil.ufriendly.data.Task
import edu.unicauca.aplimovil.ufriendly.ui.components.*
import edu.unicauca.aplimovil.ufriendly.ui.theme.UFriendlyTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun TaskScreen(
    tasks: List<Task>,
    onAddClick: () -> Unit,
) {
    //Variable para saber que botón está seleccionado
    var filterSelected by remember { mutableStateOf("All") }
    var taskList by remember { mutableStateOf(tasks) }

    // Filtrar tareas según selección
    val filteredTasks = when (filterSelected) {
        "Pending"  -> taskList.filter { !it.isDone }
        "Done"     -> taskList.filter { it.isDone }
        "Expired"  -> taskList.filter { isExpired(it.dueDate) && !it.isDone }
        else       -> taskList
    }

    // Separar tareas de hoy y próximas
    val todayTasks = filteredTasks.filter { isToday(it.dueDate) }
    val upcomingTasks = filteredTasks.filter { !isToday(it.dueDate) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        topBar = { TopBar(stringResource(R.string.task_label)) },
        bottomBar = { BottomBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                shape = MaterialTheme.shapes.extraLarge,
                containerColor = MaterialTheme.colorScheme.tertiaryContainer
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add new entry"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // Botones de filtro
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    text = stringResource(R.string.all_label),
                    isSelected = filterSelected == "All",
                    onClick = { filterSelected = "All" }
                )
                Button(
                    text = stringResource(R.string.pending_task),
                    isSelected = filterSelected == "Pending",
                    onClick = { filterSelected = "Pending" }
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
                                taskList = taskList.map { //Crea una nueva lista cambiando solo la tarea que fue checkeada
                                    if (it == task) it.copy(isDone = checked) else it
                                }
                            }
                        )
                    }
                }

                // Sección "Próxima"
                if (upcomingTasks.isNotEmpty()) {
                    item {
                        TaskSection(
                            title = stringResource(R.string.upcoming_label),
                            tasks = upcomingTasks,
                            onCheckedChange = { task, checked ->
                                taskList = taskList.map {
                                    if (it == task) it.copy(isDone = checked) else it
                                }
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
                                text = "No hay tareas",
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

@Preview(showBackground = true)
@Composable
fun TaskScreenPreview() {
    val subject = Subject("Cálculo I", listOf("Lunes 8-11"), "Juan Pérez", 80, 2.9, Color(0xFFE8D08A))
    val subject2 = Subject("Programación Avanzada", listOf("Martes 10-12"), "Ana García", 75, 3.5, Color(0xFF90CAF9))
    val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    val tasks = listOf(
        Task("Preparar presentación de cálculo", "Desc", today, false, subject),
        Task("Estudiar ondas", "Desc", today, false, subject),
        Task("Proyecto de programación", "Desc", "2023-10-28", false, subject2),
        Task("Proyecto de automatización", "Desc", "2023-10-28", false, subject2)
    )
    UFriendlyTheme {
        TaskScreen(tasks = tasks, onAddClick = {})
    }
}
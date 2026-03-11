// TaskSection.kt - Sección con título y lista de tareas
package edu.unicauca.aplimovil.ufriendly.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.unicauca.aplimovil.ufriendly.data.Subject
import edu.unicauca.aplimovil.ufriendly.data.Task
import edu.unicauca.aplimovil.ufriendly.ui.theme.UFriendlyTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun TaskSection(
    title: String,
    tasks: List<Task>,
    onCheckedChange: (Task, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    if (tasks.isEmpty()) return

    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        tasks.forEach { task ->
            TaskItem(
                task = task,
                onCheckedChange = { checked -> onCheckedChange(task, checked) }
            )
        }
    }
}
@Preview
@Composable
fun TaskSectionPreview(){
    val subject = Subject("Cálculo I", listOf("Lunes 8-11"), "Juan Pérez", 80, 2.9, Color(0xFFE8D08A))
    val subject2 = Subject("Programación Avanzada", listOf("Martes 10-12"), "Ana García", 75, 3.5, Color(0xFF90CAF9))
    val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    val tasks = listOf(
        Task("Preparar presentación de cálculo", "Desc", today, false, subject),
        Task("Estudiar ondas", "Desc", today, false, subject),
        Task("Proyecto de programación", "Desc", "2023-10-28", false, subject2),
        Task("Proyecto de automatización", "Desc", "2023-10-28", false, subject2)
    )
    UFriendlyTheme() {
        TaskSection(title = "Hoy", tasks = tasks, onCheckedChange = { _, _ -> })

    }
}
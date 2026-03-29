// TaskItem.kt - Componente de tarea individual
package edu.unicauca.aplimovil.ufriendly.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.unicauca.aplimovil.ufriendly.data.entity.Subject
import edu.unicauca.aplimovil.ufriendly.data.entity.Task
import edu.unicauca.aplimovil.ufriendly.ui.theme.UFriendlyTheme

@Composable
fun TaskItem(
    task: Task,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp) //Sombras
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.isDone,
                onCheckedChange = {checked -> onCheckedChange(checked)},
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.tertiary,
                    uncheckedColor = MaterialTheme.colorScheme.outline
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = task.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "${task.subjectId} - ${task.dueDate}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
/*
@Preview
@Composable
fun TaskItemPreview(){
    val subject = Subject("Cálculo I", listOf("Lunes 8-11"), "Juan Pérez", 80, 2.9, Color(0xFFE8D08A), null)
    val task = Task("Preparar presentación de cálculo", "Desc", "2023-10-2",false,subject)
    UFriendlyTheme {
        TaskItem(task = task, onCheckedChange = {})
    }

}*/
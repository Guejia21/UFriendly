package edu.unicauca.aplimovil.ufriendly.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.ui.text.font.FontWeight

/**
 * Componente que permite seleccionar los días de la semana en los que se dicta una materia.
 *
 * @param selectedDays Conjunto de días actualmente seleccionados.
 * @param onDaysSelectedChange Callback para notificar cambios en la selección de días.
 * @param modifier Modificador para ajustar el diseño o comportamiento del contenedor.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ClassDaysSelector(
    selectedDays: Set<String>,
    onDaySelectedChange: (Set<String>) -> Unit,
    modifier: Modifier = Modifier
) {
    val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
    val firstRow = daysOfWeek.take(4)
    val secondRow = daysOfWeek.drop(4)

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Days of class",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            firstRow.forEach { day ->
                DayChip(
                    day = day,
                    selected = selectedDays.contains(day),
                    onToggle = {
                        val newSelection = if (selectedDays.contains(day))
                            selectedDays - day else selectedDays + day
                        onDaySelectedChange(newSelection)
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            secondRow.forEach { day ->
                DayChip(
                    day = day,
                    selected = selectedDays.contains(day),
                    onToggle = {
                        val newSelection = if (selectedDays.contains(day))
                            selectedDays - day else selectedDays + day
                        onDaySelectedChange(newSelection)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DayChip(
    day: String,
    selected: Boolean,
    onToggle: () -> Unit
) {
    FilterChip(
        selected = selected,
        onClick = onToggle,
        label = { Text(text = day, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.scrim) },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}
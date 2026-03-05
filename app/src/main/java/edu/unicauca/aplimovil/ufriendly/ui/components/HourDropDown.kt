package edu.unicauca.aplimovil.ufriendly.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

/**
 * Componente que muestra un selector de hora desplegable (Dropdown).
 * Permite al usuario elegir una hora de una lista generada automáticamente.
 *
 * @param label Título descriptivo para el selector.
 * @param selectedHour La hora que se muestra como seleccionada actualmente.
 * @param onHourSelected Callback que se ejecuta cuando el usuario elige una hora de la lista.
 * @param modifier Modificador para ajustar el diseño del componente.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HourDropDown(
    label: String,
    selectedHour: String,
    onHourSelected: (String) -> Unit,
    modifier: Modifier = Modifier
){
    var expanded by remember { mutableStateOf(false) }
    val hours = remember { genHours()}

    Column(modifier = modifier.fillMaxWidth()){
        Text(text=label, style = MaterialTheme.typography.labelMedium, modifier = Modifier.padding(bottom = 4.dp),
            color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedHour,
                onValueChange = { },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor() // Asegura que el menú se posicione correctamente
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                hours.forEach { hour ->
                    DropdownMenuItem(
                        text = { Text(text = hour) },
                        onClick = {
                            onHourSelected(hour)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

/**
 * Genera una lista de horas en formato de 12 horas (AM/PM).
 * @return Una lista de Strings representando las horas desde las 7:00 AM hasta las 8:00 PM.
 */
fun genHours(): List<String>{
    val hours = mutableListOf<String>()
    for (h in 7..20) {
        val displayHour = if (h <= 12) h else h - 12
        val amPm = if (h < 12) "AM" else "PM"
        hours.add("$displayHour:00 $amPm")
    }
    return hours
}

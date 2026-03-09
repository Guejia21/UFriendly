package edu.unicauca.aplimovil.ufriendly.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

/**
 * Selector de día único de la semana.
 * Muestra los días en una cuadrícula (Grid) y resalta el día seleccionado.
 *
 * @param daySelected El día actualmente seleccionado (ej: "Monday").
 * @param onDaySelected Callback que se ejecuta al elegir un día.
 */
@Composable
fun DaySelector(
    daySelected: String?,
    onDaySelected: (String) -> Unit
) {
    val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
    
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Day of class",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            letterSpacing = 0.08.em
        )
        
        // Dividimos los días en filas de 3
        val rows = days.chunked(3)
        rows.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                row.forEach { day ->
                    val select = day == daySelected
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .border(
                                width = 1.5.dp,
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(11.dp)
                            )
                            .background(
                                color = if (select) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.onPrimary,
                                shape = RoundedCornerShape(11.dp)
                            )
                            .clickable { onDaySelected(day) }
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = day,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (select) MaterialTheme.colorScheme.onPrimary
                            else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

package edu.unicauca.aplimovil.ufriendly.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Tarjeta informativa para mostrar un horario específico.
 * Presenta el día y el rango de horas con un estilo limpio.
 *
 * @param day Día de la semana.
 * @param hourStart Hora de inicio.
 * @param hourEnd Hora de fin.
 * @param onDelete Acción para eliminar este horario de la lista.
 */
@Composable
fun ScheduleCard(
    day: String,
    startHour: String,
    endHour: String,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceContainerLowest, shape = RoundedCornerShape(12.dp))
            //.border(1.5.dp, RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Badge del día
        Box(
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(horizontal = 10.dp, vertical = 4.dp)
        ) {
            Text(
                text = day,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Horas
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "$startHour – $endHour",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Toca para editar",
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurface

            )
        }

        // Botón eliminar
        IconButton(
            onClick = onDelete,
            modifier = Modifier.size(24.dp)
        ) {
            Text(
                text = "✕",
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp
            )
        }
    }
}
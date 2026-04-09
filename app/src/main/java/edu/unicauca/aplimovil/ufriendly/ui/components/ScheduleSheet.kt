package edu.unicauca.aplimovil.ufriendly.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import edu.unicauca.aplimovil.ufriendly.ui.theme.UFriendlyTheme


/**
 * Diálogo modal para la creación de horarios.
 * Combina el [DaySelector] y dos [HourPicker] para definir una sesión de clase.
 *
 * @param onDismiss Callback para cerrar el modal sin guardar.
 * @param onConfirm Callback que devuelve el día y el rango de horas seleccionado.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleSheet(
    onDismiss: () -> Unit,
    onConfirm: (dia: String, inicio: String, fin: String) -> Unit
){
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var daySelected by remember { mutableStateOf<String?>(null) }
    var hourStart by remember { mutableStateOf("7:00 AM") }
    var hourEnd by remember { mutableStateOf("9:00 AM") }

    ModalBottomSheet(
        onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.onPrimary,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            //titulo
            Text(
                text = "Add schedule",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            //Selector de dias
            DaySelector(
                daySelected = daySelected,
                onDaySelected = { daySelected = it }
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ){
                //Selector de hora inicio
                HourPicker(
                    label = "Start time",
                    hourSelected = hourStart,
                    onSelect = { hourStart = it },
                    modifier = Modifier.weight(1f)

                )
                //Selector de hora fin
                HourPicker(
                    label = "End time",
                    hourSelected = hourEnd,
                    onSelect = { hourEnd = it },
                    modifier = Modifier.weight(1f)
                )
            }
            //Boton confirmar horario
            Button(
                "Set schedule",
                isSelected = false,
                onClick = {
                    daySelected?.let { onConfirm(it, hourStart, hourEnd) }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = daySelected != null
            )
        }
    }
}
package edu.unicauca.aplimovil.ufriendly.ui.screens

import android.R.attr.width
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.unicauca.aplimovil.ufriendly.ui.components.BottomBar
import edu.unicauca.aplimovil.ufriendly.ui.components.FormCard
import edu.unicauca.aplimovil.ufriendly.ui.components.TextBoxForm
import edu.unicauca.aplimovil.ufriendly.ui.components.TopBar
import edu.unicauca.aplimovil.ufriendly.ui.theme.UFriendlyTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.unicauca.aplimovil.ufriendly.ui.components.Button
import edu.unicauca.aplimovil.ufriendly.ui.components.ColorSelector
import edu.unicauca.aplimovil.ufriendly.ui.components.DashedBorderButton
import edu.unicauca.aplimovil.ufriendly.ui.components.ScheduleCard
import edu.unicauca.aplimovil.ufriendly.ui.components.ScheduleSheet

/**
 * Pantalla para agregar una nueva materia a la aplicación.
 *
 * Esta pantalla contiene un formulario completo que permite al usuario ingresar:
 * - Nombre de la materia.
 * - Días de la semana en los que se dicta.
 * - Horario de inicio y fin.
 * - Nombre del profesor.
 * - Color identificativo para la materia.
 */
@Composable
fun AddSubjectScreen(navController: NavHostController){
    // Estados para los campos del formulario
    var nameSubject by remember { mutableStateOf("") }
    var nameTeacher by remember { mutableStateOf("") }
    var selectedDays by remember { mutableStateOf(setOf<String>())}
    var selectedColor by remember { mutableStateOf<Color?>(null) }
    var save by remember { mutableStateOf(false) }
    var showSheet by remember { mutableStateOf(false) }
    var schedules by remember { mutableStateOf(listOf<Triple<String, String, String>>()) }
    
    Scaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        topBar = { TopBar("Add Subject") },
        bottomBar = {
            Column {
                //boton guardar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        text = "Save subject",
                        isSelected = save,
                        onClick = { save = true },
                        modifier = Modifier.size(width = 200.dp, height = 60.dp)
                    )
                }
                BottomBar(navController)
            }
                    },
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            //Formulario
            FormCard {
                //nombre materia
                TextBoxForm(
                    label = "Name",
                    placeholder = "Ex: Maths",
                    value = nameSubject,
                    onValueChange = { newText -> nameSubject = newText }
                )

                //Agregar horario
                Text(
                    text = "Class schedules",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(5.dp))
                schedules.forEach { (day, start, end) ->
                    ScheduleCard(
                        day = day,
                        hourStart = start,
                        hourEnd = end,
                        onDelete = { schedules = schedules - Triple(day, start, end) }
                    )
                    Spacer(Modifier.height(7.dp))
                }

                DashedBorderButton(onClick = {showSheet = true})

                //nombre profesor
                TextBoxForm(
                    label = "Teacher",
                    placeholder = "Ex: John Doe",
                    value = nameTeacher,
                    onValueChange = { newText -> nameTeacher = newText }
                )

                //Color
                ColorSelector(
                    selectedColor = selectedColor,
                    onColorSelected = { selectedColor = it }
                )
            }
            if(showSheet){
                ScheduleSheet(
                    onDismiss = { showSheet = false },
                    onConfirm = { day, start, end ->
                        schedules = schedules + Triple(day, start, end)
                        showSheet = false
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddSubjectScreenPreview(){
    UFriendlyTheme {
        AddSubjectScreen(navController = rememberNavController())
    }
}

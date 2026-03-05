package edu.unicauca.aplimovil.ufriendly.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import edu.unicauca.aplimovil.ufriendly.ui.components.Button
import edu.unicauca.aplimovil.ufriendly.ui.components.ClassDaysSelector
import edu.unicauca.aplimovil.ufriendly.ui.components.ColorSelector
import edu.unicauca.aplimovil.ufriendly.ui.components.HourDropDown

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
fun AddSubjectScreen(){
    // Estados para los campos del formulario
    var nameSubject by remember { mutableStateOf("") }
    var nameTeacher by remember { mutableStateOf("") }
    var selectedDays by remember { mutableStateOf(setOf<String>())}
    var selectedColor by remember { mutableStateOf<Color?>(null) }
    var save by remember { mutableStateOf(false) }
    
    Scaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        topBar = { TopBar("Add Subject") },
        bottomBar = { BottomBar() },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
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

                //Dias de clase
                ClassDaysSelector(
                    selectedDays = selectedDays,
                    onDaySelectedChange = { selectedDays = it}
                )

                //hora inicio/fin
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    HourDropDown(label = "Hour start", selectedHour = "7:00 AM", onHourSelected ={}, modifier = Modifier.weight(1f))
                    HourDropDown(label = "Hour end", selectedHour = "8:00 PM", onHourSelected ={}, modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(10.dp))
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
            //boton guardar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    text = "Save subject",
                    isSelected = save,
                    onClick = { save = true },
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddSubjectScreenPreview(){
    UFriendlyTheme {
        AddSubjectScreen()
    }
}

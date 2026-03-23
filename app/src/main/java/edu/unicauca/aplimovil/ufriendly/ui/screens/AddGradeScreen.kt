package edu.unicauca.aplimovil.ufriendly.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.unicauca.aplimovil.ufriendly.ui.components.TopBar
import edu.unicauca.aplimovil.ufriendly.R
import edu.unicauca.aplimovil.ufriendly.ui.components.ComboBox
import edu.unicauca.aplimovil.ufriendly.ui.components.DatePickerDocked
import edu.unicauca.aplimovil.ufriendly.ui.components.FormCard
import edu.unicauca.aplimovil.ufriendly.ui.components.TextBoxForm
import edu.unicauca.aplimovil.ufriendly.ui.components.convertMillisToDate
import edu.unicauca.aplimovil.ufriendly.ui.theme.UFriendlyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGradeScreen(
    navController: NavHostController
){
    var nameGrade by remember { mutableStateOf("") }
    var valueGrade by remember { mutableStateOf("") }
    var weightGrade by remember { mutableStateOf("") }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    GenericScreen(
        navController = navController,
        topBar = { TopBar(stringResource(R.string.add_grade_label)) }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            FormCard (
                buttonLabel = stringResource(R.string.save_grade_label),
                onClick = {/*saveSubject() o algo asi*/}
            ){
                //Nombre calificación
                TextBoxForm(
                    label = "Name",
                    placeholder = "Ex: First math test",
                    value = nameGrade,
                    onValueChange = { newText -> nameGrade = newText }
                )

                //calificación obtenida
                TextBoxForm(
                    label = "Value",
                    placeholder = "Ex: 4.5",
                    value = valueGrade,
                    onValueChange = { newText -> valueGrade = newText }
                )

                //Peso de la calificación
                TextBoxForm(
                    label = "Weight",
                    placeholder = "Ex: 0.35",
                    value = weightGrade,
                    onValueChange = { newText -> weightGrade = newText }
                )

                //fecha
                DatePickerDocked(
                    selectedDate = selectedDate,
                    datePickerState = datePickerState
                )

                //Materia asociada
                ComboBox(
                    options = subjects.map { it.name },
                    label = stringResource(R.string.subject_label)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddGradeScreenPreview(){
    UFriendlyTheme() {
        AddGradeScreen(navController = rememberNavController())
    }
}
package edu.unicauca.aplimovil.ufriendly.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import edu.unicauca.aplimovil.ufriendly.ui.components.TopBar
import edu.unicauca.aplimovil.ufriendly.R
import edu.unicauca.aplimovil.ufriendly.data.entity.Grade
import edu.unicauca.aplimovil.ufriendly.data.entity.Subject
import edu.unicauca.aplimovil.ufriendly.data.relation.SubjectWithSchedules
import edu.unicauca.aplimovil.ufriendly.ui.components.ComboBox
import edu.unicauca.aplimovil.ufriendly.ui.components.DatePickerDocked
import edu.unicauca.aplimovil.ufriendly.ui.components.FormCard
import edu.unicauca.aplimovil.ufriendly.ui.components.TextBoxForm
import edu.unicauca.aplimovil.ufriendly.ui.components.convertMillisToDate
import kotlin.collections.map

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGradeScreen(
    navController: NavHostController,
    subjects: List<SubjectWithSchedules>,
    addGradeItem: (Grade) -> Unit = {}
){
    var nameGrade by remember { mutableStateOf("") }
    var valueGrade by remember { mutableDoubleStateOf(0.0) }
    var weightGrade by remember { mutableDoubleStateOf(0.0) }
    val datePickerState = rememberDatePickerState()
    var gradeSubjectId by remember  { mutableStateOf<Int?>(null) }
    var selectedDate = datePickerState.selectedDateMillis?.let {
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
            FormCard<Grade>(
                buttonLabel = stringResource(R.string.save_grade_label),
                itemProvider = { Grade(
                    name=nameGrade,
                    value = valueGrade,
                    weight = weightGrade,
                    date = selectedDate,
                    subjectId = gradeSubjectId,
                )},
                addNewItem = addGradeItem
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
                    value = valueGrade.toString(),
                    onValueChange = { newText -> valueGrade = newText.toDouble()},
                    isNumberField = true
                )
                //TODO Si pongo un punto se estalla la aplicación !!!!
                //Peso de la calificación
                TextBoxForm(
                    label = "Weight",
                    placeholder = "Ex: 0.35",
                    value = weightGrade.toString(),
                    onValueChange = { newText -> weightGrade = newText.toDouble() },
                    isNumberField = true
                )

                //fecha
                DatePickerDocked(
                    value = selectedDate,
                    onValueChange = { newText -> selectedDate = newText },
                    datePickerState = datePickerState
                )

                //Materia asociada
                ComboBox(
                    options = subjects.map { it.subject.name },
                    label = stringResource(R.string.subject_label),
                    placeholder = stringResource(R.string.select_subject_label),
                    onValueChange = {newText -> gradeSubjectId =
                        subjects.find { it.subject.name == newText }?.subject?.id ///cambiar
                    }
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun AddGradeScreenPreview(){
//    UFriendlyTheme() {
//        AddGradeScreen(navController = rememberNavController())
//    }
//}
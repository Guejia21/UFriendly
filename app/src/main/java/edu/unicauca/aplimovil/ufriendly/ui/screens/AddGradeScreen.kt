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
import androidx.navigation.NavHostController
import edu.unicauca.aplimovil.ufriendly.ui.components.TopBar
import edu.unicauca.aplimovil.ufriendly.R
import edu.unicauca.aplimovil.ufriendly.data.SaveableItem
import edu.unicauca.aplimovil.ufriendly.data.entity.Grade
import edu.unicauca.aplimovil.ufriendly.data.relation.SubjectWithSchedules
import edu.unicauca.aplimovil.ufriendly.ui.components.ComboBox
import edu.unicauca.aplimovil.ufriendly.ui.components.DatePickerDocked
import edu.unicauca.aplimovil.ufriendly.ui.components.FormCard
import edu.unicauca.aplimovil.ufriendly.ui.components.TextBoxForm
import edu.unicauca.aplimovil.ufriendly.ui.components.convertMillisToDate
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGradeScreen(
    navController: NavHostController,
    subjects: List<SubjectWithSchedules>,
    addGradeItem: (Grade) -> Unit = {}
){
    var nameGrade by remember { mutableStateOf("") }
    var valueGrade by remember { mutableStateOf("") }
    var weightGrade by remember { mutableStateOf("") }
    val datePickerState = rememberDatePickerState()
    var gradeSubjectId by remember  { mutableStateOf<Int?>(null) }
    val selectedDateStr = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    GenericScreen(
        navController = navController,
        topBar = { TopBar(stringResource(R.string.add_grade_label)) }
    ) {
            FormCard(
                buttonLabel = stringResource(R.string.save_grade_label),
                itemToSave =  Grade(
                    name=nameGrade,
                    value = valueGrade.toDoubleOrNull() ?: 0.0,
                    weight = weightGrade.toDoubleOrNull() ?: 0.0,
                    date = datePickerState.selectedDateMillis?.let { Date(it) },
                    subjectId = gradeSubjectId,
                ),
                addNewItem = addGradeItem as (SaveableItem) -> Unit,
                afterSave = {
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("success_message", "Grade saved successfully")
                    navController.popBackStack()
                }
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
                    onValueChange = { newText -> valueGrade = newText},
                    isNumberField = true
                )

                //Peso de la calificación
                TextBoxForm(
                    label = "Weight",
                    placeholder = "Ex: 0.35",
                    value = weightGrade,
                    onValueChange = { newText -> weightGrade = newText },
                    isNumberField = true
                )

                //fecha
                DatePickerDocked(
                    value = selectedDateStr,
                    onValueChange = { },
                    datePickerState = datePickerState
                )

                //Materia asociada
                ComboBox(
                    options = subjects.map { it.subject.name },
                    label = stringResource(R.string.subject_label),
                    placeholder = stringResource(R.string.select_subject_label),
                    onValueChange = {newText -> gradeSubjectId =
                        subjects.find { it.subject.name == newText }?.subject?.id
                    }
                )
            }
        }
    }


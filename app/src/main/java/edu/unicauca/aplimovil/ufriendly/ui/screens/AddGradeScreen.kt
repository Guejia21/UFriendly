package edu.unicauca.aplimovil.ufriendly.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import edu.unicauca.aplimovil.ufriendly.ui.components.TopBar
import edu.unicauca.aplimovil.ufriendly.R
import edu.unicauca.aplimovil.ufriendly.data.SaveableItem
import edu.unicauca.aplimovil.ufriendly.data.entity.Grade
import edu.unicauca.aplimovil.ufriendly.data.relation.SubjectFullInfo
import edu.unicauca.aplimovil.ufriendly.ui.components.ComboBox
import edu.unicauca.aplimovil.ufriendly.ui.components.DatePickerDocked
import edu.unicauca.aplimovil.ufriendly.ui.components.FormCard
import edu.unicauca.aplimovil.ufriendly.ui.components.TextBoxForm
import edu.unicauca.aplimovil.ufriendly.ui.components.convertMillisToDate
import java.util.Date

/**
 * Pantalla para añadir una nueva calificación.
 * Realiza validaciones automáticas a través de la interfaz [SaveableItem].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGradeScreen(
    navController: NavHostController,
    subjects: List<SubjectFullInfo>,
    addGradeItem: (Grade) -> Unit = {}
){
    var nameGrade by remember { mutableStateOf("") }
    var valueGrade by remember { mutableStateOf("") }
    var weightGrade by remember { mutableStateOf("") }
    val datePickerState = rememberDatePickerState()
    var gradeSubjectId by remember  { mutableIntStateOf(0) }
    
    val selectedDateStr = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    GenericScreen(
        navController = navController,
        topBar = { TopBar(stringResource(R.string.add_grade_label)) }
    ) {

            if (subjects.isEmpty()) {
                // Validación de compañero: Mensaje si no hay materias
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Text(
                        text = "Debes crear una materia primero para poder añadir notas.",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            } else {
                FormCard(
                    buttonLabel = stringResource(R.string.save_grade_label),
                    itemToSave = Grade(
                        name = nameGrade,
                        value = valueGrade.toDoubleOrNull() ?: 0.0,
                        weight = weightGrade.toDoubleOrNull() ?: 0.0,
                        date = datePickerState.selectedDateMillis?.let { Date(it) },
                        subjectId = gradeSubjectId,
                    ),
                    addNewItem = { item -> addGradeItem(item as Grade) },
                    afterSave = {
                        // Tu lógica: Mensaje de éxito al guardar
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("success_message", "Grade saved successfully")
                        navController.popBackStack()
                    }
                ){
                    // Formulario
                    TextBoxForm(
                        label = "Name",
                        placeholder = "Ex: First math test",
                        value = nameGrade,
                        onValueChange = { nameGrade = it }
                    )

                    TextBoxForm(
                        label = "Value",
                        placeholder = "Ex: 4.5",
                        value = valueGrade,
                        onValueChange = { valueGrade = it },
                        isNumberField = true
                    )

                    TextBoxForm(
                        label = "Weight 0-1",
                        placeholder = "Ex: 0.35",
                        value = weightGrade,
                        onValueChange = { newValue ->
                            val parsed = newValue.toDoubleOrNull()
                            if (newValue.isEmpty() || (parsed != null && parsed <= 1.0)) {
                                weightGrade = newValue
                            }
                        },
                        isNumberField = true
                    )

                    DatePickerDocked(
                        value = selectedDateStr,
                        onValueChange = { },
                        datePickerState = datePickerState
                    )

                    ComboBox(
                        options = subjects.map { it.subject.name },
                        label = stringResource(R.string.subject_label),
                        placeholder = stringResource(R.string.select_subject_label),
                        onValueChange = { newText ->
                            gradeSubjectId = subjects.find { it.subject.name == newText }?.subject?.id ?: 0
                        }
                    )
                }
            }

    }
}

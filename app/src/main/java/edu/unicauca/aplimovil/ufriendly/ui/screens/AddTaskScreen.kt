package edu.unicauca.aplimovil.ufriendly.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import edu.unicauca.aplimovil.ufriendly.R
import edu.unicauca.aplimovil.ufriendly.data.SaveableItem
import edu.unicauca.aplimovil.ufriendly.data.entity.Task
import edu.unicauca.aplimovil.ufriendly.data.relation.SubjectWithSchedules
import edu.unicauca.aplimovil.ufriendly.ui.components.ComboBox
import edu.unicauca.aplimovil.ufriendly.ui.components.DatePickerDocked
import edu.unicauca.aplimovil.ufriendly.ui.components.FormCard
import edu.unicauca.aplimovil.ufriendly.ui.components.TextBoxForm
import edu.unicauca.aplimovil.ufriendly.ui.components.TopBar
import edu.unicauca.aplimovil.ufriendly.ui.components.convertMillisToDate
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    navController: NavHostController,
    subjects: List<SubjectWithSchedules>,
    addTaskItem: (Task) -> Unit = {},
    ){
    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var taskSubjectId by remember  { mutableStateOf<Int?>(null) }
    val datePickerState = rememberDatePickerState()
    val selectedDateStr = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    GenericScreen(
        navController = navController,
        topBar = { TopBar(stringResource(R.string.add_task_label)) },
    )
    {
        FormCard(
            buttonLabel = stringResource(R.string.save_task_label),
            itemToSave = Task(
                name = taskName,
                description = taskDescription,
                dueDate = datePickerState.selectedDateMillis?.let { Date(it) },
                subjectId = taskSubjectId
            ),
            addNewItem = addTaskItem as (SaveableItem) -> Unit,
            afterSave = {
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set("success_message", "Task saved successfully")
                navController.popBackStack()
            }
        ){
            TextBoxForm(
                label = "Name",
                placeholder = "Ex: Calculus project",
                value = taskName,
                onValueChange = { newText -> taskName = newText }
            )
            TextBoxForm(
                label = "Description",
                placeholder = "Ex: Complete the project",
                value = taskDescription,
                singleLine = false,
                onValueChange = { newText -> taskDescription = newText }
            )
            DatePickerDocked(
                value = selectedDateStr,
                onValueChange = { },
                datePickerState = datePickerState,
            )
            ComboBox(
                options = subjects.map { it.subject.name },
                label = stringResource(R.string.subject_label),
                placeholder = stringResource(R.string.select_subject_label),
                onValueChange = {newText -> taskSubjectId =
                    subjects.find { it.subject.name == newText }?.subject?.id
                }
            )
        }
    }
}

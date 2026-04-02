package edu.unicauca.aplimovil.ufriendly.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.unicauca.aplimovil.ufriendly.R
import edu.unicauca.aplimovil.ufriendly.data.SaveableItem
import edu.unicauca.aplimovil.ufriendly.data.entity.Subject
import edu.unicauca.aplimovil.ufriendly.data.entity.Task
import edu.unicauca.aplimovil.ufriendly.data.relation.SubjectWithSchedules
import edu.unicauca.aplimovil.ufriendly.data.repository.SubjectRepository
import edu.unicauca.aplimovil.ufriendly.data.repository.TaskRepository
import edu.unicauca.aplimovil.ufriendly.ui.components.ComboBox
import edu.unicauca.aplimovil.ufriendly.ui.components.DatePickerDocked
import edu.unicauca.aplimovil.ufriendly.ui.components.FormCard
import edu.unicauca.aplimovil.ufriendly.ui.components.TextBoxForm
import edu.unicauca.aplimovil.ufriendly.ui.components.TopBar
import edu.unicauca.aplimovil.ufriendly.ui.components.convertMillisToDate
import edu.unicauca.aplimovil.ufriendly.ui.theme.UFriendlyTheme
import edu.unicauca.aplimovil.ufriendly.ui.viewModels.SubjectViewModel
import edu.unicauca.aplimovil.ufriendly.ui.viewModels.TaskViewModel

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
    var selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    GenericScreen(
        navController = navController,
        topBar = { TopBar(stringResource(R.string.add_task_label)) },
    )
    {
        FormCard(
            buttonLabel = stringResource(R.string.save_task_label),
            itemToSave = Task(name=taskName, description =  taskDescription, dueDate = selectedDate, subjectId = taskSubjectId),
            addNewItem = addTaskItem as (SaveableItem) -> Unit
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
                onValueChange = { newText -> taskDescription = newText }
            )
            DatePickerDocked(
                value = selectedDate,
                onValueChange = { newText -> selectedDate = newText },
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
/*@Preview
@Composable
fun AddTaskScreenPreview(){
    UFriendlyTheme {
        AddTaskScreen(navController = rememberNavController(), subjects = listOf(Subject("Cálculo I", listOf("Lunes 8-11"), "Juan Pérez", 80, 2.9, color = Color(0xFFE8D08A), null)))
    }
}*/
package edu.unicauca.aplimovil.ufriendly.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.unicauca.aplimovil.ufriendly.ui.components.FormCard
import edu.unicauca.aplimovil.ufriendly.ui.components.TextBoxForm
import edu.unicauca.aplimovil.ufriendly.ui.components.TopBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import edu.unicauca.aplimovil.ufriendly.R
import edu.unicauca.aplimovil.ufriendly.data.SaveableItem
import edu.unicauca.aplimovil.ufriendly.data.entity.ClassSchedule
import edu.unicauca.aplimovil.ufriendly.data.entity.Subject
import edu.unicauca.aplimovil.ufriendly.data.relation.SubjectWithSchedules
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
fun AddSubjectScreen(
    navController: NavHostController,
    addSubjectItem: (SubjectWithSchedules) -> Unit
){
    //TODO Cambiar los estados a un ViewModel
    // Estados para los campos del formulario
    var nameSubject by remember { mutableStateOf("") }
    var nameTeacher by remember { mutableStateOf("") }
    var classScheduleSubjectId by remember { mutableIntStateOf(0) }
    var selectedColor by remember { mutableStateOf<Color?>(null) }
    var showSheet by remember { mutableStateOf(false) }
    var schedules by remember { mutableStateOf(listOf<Triple<String, String, String>>()) }
    
    GenericScreen(
        navController = navController,
        topBar = { TopBar(stringResource(R.string.add_subject_label)) },
    ) {
            //Formulario
            FormCard(
                buttonLabel = stringResource(R.string.save_subject_label),
                itemToSave = SubjectWithSchedules(
                    subject = Subject(
                        name=nameSubject,
                        teacher = nameTeacher,
                        color = "",
                    ),
                    classSchedules = schedules.map { (day, startHour, endHour) ->
                        ClassSchedule(
                            day = day,
                            startHour = startHour,
                            endHour = endHour,
                            subjectId = classScheduleSubjectId
                        )
                    }
                ),
                addNewItem = addSubjectItem as (SaveableItem) -> Unit,
                afterSave = {
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("success_message", "Subject saved successfully")
                    navController.popBackStack()
                }
            ){
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
                    schedules.forEach { (day, startHour, endHour) ->
                    ScheduleCard(
                        day = day,
                        startHour = startHour,
                        endHour = endHour,
                        onDelete = { schedules = schedules - Triple(day, startHour, endHour) }
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
                    onConfirm = { day, startHour, endHour ->
                        schedules = schedules + Triple(day, startHour, endHour)
                        showSheet = false
                    }
                )
            }
        }
    }


package edu.unicauca.aplimovil.ufriendly.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.unicauca.aplimovil.ufriendly.R
import edu.unicauca.aplimovil.ufriendly.data.entity.Grade
import edu.unicauca.aplimovil.ufriendly.data.entity.Subject
import edu.unicauca.aplimovil.ufriendly.data.relation.GradeWithSubject
import edu.unicauca.aplimovil.ufriendly.ui.components.GradesContent
import edu.unicauca.aplimovil.ufriendly.ui.components.TopBar
import edu.unicauca.aplimovil.ufriendly.ui.theme.UFriendlyTheme

/**
 * Pantalla principal de calificaciones.
 * Orquestra la visualización de la lista de materias y sus respectivas notas.
 *
 * @param subjects Lista de materias a mostrar en la pantalla.
 * @param navController Controlador de navegación para gestionar el flujo de la app.
 */
@Composable
fun GradesScreen(
    grades: List<GradeWithSubject>,
    navController: NavHostController,
){
    GenericScreen(
        navController = navController,
        topBar = { TopBar(stringResource(R.string.grades_label)) }
    ) {
        if(grades.isEmpty()){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.no_grades_label),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }else{
            GradesContent(grades)
        }
    }
}

/**
 * Previsualización de [GradesScreen] con datos de prueba.
 */
//@Preview(showBackground = true)
//@Composable
//fun GradesScreenPreview(){
//    val subject = Subject("Cálculo I", listOf("Lunes 8-11"), "Juan Pérez", 80, 2.9, Color(0xFFE8D08A), listOf(Grade("1er parcial", 4.2, 0.35, "2023-10-28"), Grade("2do parcial", 4.5, 0.35, "2023-10-28")))
//    val subject2 = Subject("Programación Avanzada", listOf("Martes 10-12"), "Ana García", 75, 3.5, Color(0xFF90CAF9), listOf(Grade("1er parcial", 4.2, 0.35, "2023-10-28")))
//    val subjects = listOf(subject, subject2)
//
//    UFriendlyTheme {
//        GradesScreen(
//            subjects = subjects,
//            navController = rememberNavController()
//        )
//    }
//}

package edu.unicauca.aplimovil.ufriendly.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.unicauca.aplimovil.ufriendly.data.Subject
import edu.unicauca.aplimovil.ufriendly.ui.components.BottomBar
import edu.unicauca.aplimovil.ufriendly.ui.components.SearchBar
import edu.unicauca.aplimovil.ufriendly.ui.components.TopBar
import edu.unicauca.aplimovil.ufriendly.ui.components.Button
import edu.unicauca.aplimovil.ufriendly.ui.components.SubjectFullCard
import edu.unicauca.aplimovil.ufriendly.ui.theme.UFriendlyTheme

/**
 * Pantalla principal de la sección de Materias.
 * Gestiona el estado de búsqueda y el filtrado entre materias en curso y terminadas.
 *
 * @param subjects Lista total de materias disponibles.
 * @param onAddClick Acción a ejecutar cuando se presiona el botón flotante para añadir una materia.
 */
@Composable
fun SubjectScreen(
    subjects: List<Subject>,
    navController: NavHostController,
    onAddClick: () -> Unit,
) {
    val textSearch = remember { mutableStateOf("") }
    var mostrarCursando by remember { mutableStateOf(true) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        topBar = {TopBar("Subjects")},
        bottomBar = { BottomBar(navController) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick, shape = MaterialTheme.shapes.extraLarge, containerColor = MaterialTheme.colorScheme.tertiaryContainer) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add new entry"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            //Barra de búsqueda
            SearchBar(
                textSearch.value,
                { textSearch.value = it },
                { query -> println("Searching: $query") },
                "Search subjects..."
            )
            //Botones
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    text = "Cursando",
                    isSelected = mostrarCursando,
                    onClick = { mostrarCursando = true },
                    modifier = Modifier.padding(end = 8.dp)
                )
                Button(
                    text = "Cursadas",
                    isSelected = !mostrarCursando,
                    onClick = { mostrarCursando = false },
                    modifier = Modifier.padding(start = 8.dp)
                )
        }
            // Contenido cuerpo scaffold
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ){
                if (mostrarCursando) {
                    subjects.filter { it.completionPercentage < 100 }.take(5).forEach { subject ->
                        SubjectFullCard(subject)
                    }
                }else{
                    subjects.filter { it.completionPercentage == 100 }.take(5).forEach { subject ->
                        SubjectFullCard(subject)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SubjectScreenPreview() {
    val subjects = listOf(
        Subject("Matemáticas", listOf("Lunes 8-11", "Miércoles 8-11"), "Juan Pérez", 80, 2.9, Color(0xFFE8D08A)),
        Subject("Física", listOf("Martes 11-13", "Jueves 12-14"), "María Gómez", 75, 3.2, Color(0xFFD0C3E6)),
        Subject("Química", listOf("Lunes 13-16", "Miércoles 13-16"), "Carlos Rodríguez", 60, 2.5, Color(0xFFE8D08A)),
        Subject("Historia", listOf("Martes 16-19", "Jueves 16-19"), "Ana López", 90, 4.0, Color(0xFFE8D08A)),
        Subject("Biología", listOf("Lunes 19-22", "Miércoles 19-22"), "Pedro Martínez", 70, 3.8, Color(0xFFD0C3E6)),
        Subject("Literatura", listOf("Martes 22-25", "Jueves 22-25"), "Laura Sánchez", 85, 3.7, Color(0xFFE8D08A))
    )
    UFriendlyTheme() {
        SubjectScreen(
            subjects = subjects,
            navController = rememberNavController(),
            onAddClick = {}
        )
    }
}

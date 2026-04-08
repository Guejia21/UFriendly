package edu.unicauca.aplimovil.ufriendly.ui.screens

import edu.unicauca.aplimovil.ufriendly.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import edu.unicauca.aplimovil.ufriendly.data.relation.SubjectFullInfo
import edu.unicauca.aplimovil.ufriendly.ui.components.SearchBar
import edu.unicauca.aplimovil.ufriendly.ui.components.TopBar
import edu.unicauca.aplimovil.ufriendly.ui.components.Button
import edu.unicauca.aplimovil.ufriendly.ui.components.SubjectFullCard
import edu.unicauca.aplimovil.ufriendly.ui.nav.ScreenName

/**
 * Pantalla principal de la sección de Materias.
 * Gestiona el estado de búsqueda y el filtrado entre materias en curso y terminadas.
 */
@Composable
fun SubjectScreen(
    subjects: List<SubjectFullInfo>,
    navController: NavHostController,
) {
    var textSearch by remember { mutableStateOf("") }
    var mostrarCursando by remember { mutableStateOf(true) }
    
    // Lógica de filtrado (combinada)
    val filteredSubjects = remember(subjects, mostrarCursando, textSearch) {
        subjects.filter { item ->
            val matchesStatus = if (mostrarCursando) {
                item.subject.completionPercentage < 100
            } else {
                item.subject.completionPercentage == 100
            }
            val matchesSearch = item.subject.name.contains(textSearch, ignoreCase = true)
            matchesStatus && matchesSearch
        }
    }

    GenericScreen(
        navController = navController,
        topBar = { TopBar(stringResource(R.string.subjects_label)) }
    )
    {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Barra de búsqueda
            SearchBar(
                textSearch,
                { textSearch = it },
                { query -> println("Searching: $query") },
                stringResource(R.string.search_subjects_placeholder)
            )
            
            // Botones de filtro
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

            // Contenido principal - Usamos LazyColumn para mejor rendimiento
            LazyColumn(
                modifier = Modifier.weight(1f) // Toma el espacio restante
            ) {
                if (filteredSubjects.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            contentAlignment = androidx.compose.ui.Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.no_subjects_label),
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                } else {
                    items(filteredSubjects) { subject ->
                        SubjectFullCard(
                            subject = subject,
                            onClick = {
                                // Mantenemos tu navegación al detalle
                                navController.navigate("${ScreenName.SubjectDetailScreen.name}/${subject.subject.id}")
                            }
                        )
                    }
                }
            }
        }
    }
}

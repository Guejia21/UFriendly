package edu.unicauca.aplimovil.ufriendly.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import edu.unicauca.aplimovil.ufriendly.data.entity.Subject

/**
 * Contenedor desplazable que lista las tarjetas de calificaciones para cada materia.
 *
 * @param subjects Lista de objetos [Subject] que se renderizarán como tarjetas [GradeCard].
 * @param modifier Modificador opcional para ajustar el diseño del contenedor.
 */
@Composable
fun GradesContent(
    subjects: List<Subject>,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(subjects) { subject ->
            GradeCard(subject = subject)
        }
    }
}
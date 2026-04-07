package edu.unicauca.aplimovil.ufriendly.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.unicauca.aplimovil.ufriendly.data.entity.Subject
import edu.unicauca.aplimovil.ufriendly.data.relation.SubjectWithSchedules

/**
 * Componente que muestra la información detallada de una materia en una tarjeta (Card).
 * Incluye el nombre, horarios, profesor, puntaje parcial y el porcentaje de completitud.
 *
 * @param subject Objeto de tipo [Subject] que contiene los datos de la materia a mostrar.
 */
@Composable
fun SubjectFullCard(
    subject: SubjectWithSchedules,
    onClick: () -> Unit = {}
){
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            //TODO manejar la conversión del string a color
            //containerColor = subject.color
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            // Sección izquierda: Información general y académica
            Column(
                modifier = Modifier.weight(1f)
            ){
                Text(
                    text = subject.subject.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(8.dp))
                //TODO Manejar con SubjectWithClassDates
                //subject.classDates.forEach { classDate -> Text(text = classDate) }
                Text(text = "Mr. ${subject.subject.teacher}")

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Score: ${subject.subject.score}",
                    fontSize = 18.sp
                )
            }

            // Sección derecha: Progreso porcentual
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${subject.subject.completionPercentage}%",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "Completed")
            }
        }
    }
}

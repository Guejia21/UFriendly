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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.unicauca.aplimovil.ufriendly.data.relation.SubjectFullInfo

@Composable
fun SubjectFullCard(
    subject: SubjectFullInfo,
    onClick: () -> Unit = {}
){
    // Usamos toULong(16) para que acepte valores altos (como los que empiezan por F)
    val cardColor = remember(subject.subject.color) {
        try {
            // Quitamos el prefijo 0x y espacios. toULong solo acepta los dígitos.
            val colorStr = subject.subject.color.replace("0x", "").trim()
            if (colorStr.isNotEmpty()) {
                // Color(ULong) es el constructor correcto para el .value de un color de Compose
                Color(colorStr.toULong(16))
            } else {
                Color(0xFFE0E0E0)
            }
        } catch (e: Exception) {
            // Color de respaldo si algo sale mal (un azul pastel)
            Color(0xFFBBDEFB)
        }
    }
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(modifier = Modifier.weight(1f)){
                Text(
                    text = subject.subject.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                Spacer(modifier = Modifier.height(8.dp))

                subject.classSchedules.forEach { classSchedule ->
                    Text(
                        text = "${classSchedule.day}: ${classSchedule.startHour} - ${classSchedule.endHour}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "Prof. ${subject.subject.teacher}",
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Score: ${String.format("%.2f", subject.currentScore)}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${subject.completionPercentage}%",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                Text(
                    text = "Completed",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}

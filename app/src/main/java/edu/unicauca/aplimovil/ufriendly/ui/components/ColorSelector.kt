package edu.unicauca.aplimovil.ufriendly.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

/**
 * Componente que permite seleccionar un color de una paleta predefinida.
 * Muestra los colores en dos filas y resalta el color seleccionado.
 *
 * @param selectedColor El color actualmente seleccionado (puede ser null).
 * @param onColorSelected Callback que se ejecuta cuando el usuario selecciona un color.
 * @param modifier Modificador para ajustar el diseño del contenedor.
 */
@Composable
fun ColorSelector(
    selectedColor: Color?,
    onColorSelected: (Color) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = listOf(
        Color(0xFF4A90D9), // Azul
        Color(0xFFE53935), // Rojo
        Color(0xFFE67E22), // Naranja
        Color(0xFF8E44AD), // Morado
        Color(0xFF9B59B6), // Lila
        Color(0xFF27AE60), // Verde
        Color(0xFFF1C40F), // Amarillo
        Color(0xFFEC407A), // Rosa
        Color(0xFF6D4C41), // Café
        Color(0xFFD4AC0D), // Dorado
    )

    val firstRow = colors.take(5)
    val secondRow = colors.drop(5)

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Choose a color",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(40.dp)) {
            firstRow.forEach { color ->
                ColorCircle(
                    color = color,
                    isSelected = color == selectedColor,
                    onClick = { onColorSelected(color) }
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(40.dp)) {
            secondRow.forEach { color ->
                ColorCircle(
                    color = color,
                    isSelected = color == selectedColor,
                    onClick = { onColorSelected(color) }
                )
            }
        }
    }
}

/**
 * Representación visual de una opción de color.
 *
 * @param color El color que representa este círculo/cuadrado.
 * @param isSelected Indica si esta opción es la seleccionada actualmente.
 * @param onClick Acción a ejecutar al hacer clic.
 */
@Composable
private fun ColorCircle(
    color: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(36.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color)
            .clickable { onClick() }
            .then(
                if (isSelected) Modifier.border(3.dp, Color.White, CircleShape)
                else Modifier
            )
    ){}
}

package edu.unicauca.aplimovil.ufriendly.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Botón de filtro personalizado que cambia de color según su estado de selección.
 *
 * @param text El texto que se mostrará dentro del botón.
 * @param isSelected Define si el botón está en estado seleccionado para cambiar su estilo visual.
 * @param onClick Acción que se ejecuta al presionar el botón.
 * @param modifier Modificador para ajustar el diseño (márgenes, tamaño, etc.) desde el exterior.
 */
@Composable
fun Button(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = if (isSelected) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.onTertiaryContainer
        ),
        modifier = modifier
    ) {
        Text(text)
    }
}
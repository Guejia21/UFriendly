package edu.unicauca.aplimovil.ufriendly.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

/**
 * Componente genérico para campos de entrada de texto en formularios.
 * Muestra una etiqueta (Label) arriba y un campo de texto delineado (OutlinedTextField) debajo.
 *
 * @param label El texto que se muestra como título del campo.
 * @param placeholder El texto de sugerencia que aparece dentro del campo cuando está vacío.
 * @param value El valor actual del texto ingresado.
 * @param onValueChange Callback que se dispara cada vez que el usuario modifica el texto.
 */
@Composable
fun TextBoxForm(label: String, placeholder: String, value: String,isNumberField: Boolean = false, onValueChange: (String) -> Unit){
    Text(
        text = label,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold
    )
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = { Text(placeholder) },
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
        ),
        singleLine = true,
        keyboardOptions = if(isNumberField) KeyboardOptions(keyboardType = KeyboardType.Number) else KeyboardOptions.Default
    )
}

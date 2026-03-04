package edu.unicauca.aplimovil.ufriendly.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

/**
 * Barra de búsqueda personalizada con icono de lupa, botón de limpiar y acción de teclado.
 *
 * @param query El texto actual ingresado en la barra de búsqueda.
 * @param onQueryChange Callback que se dispara cada vez que el texto cambia.
 * @param onSearch Callback que se ejecuta cuando el usuario presiona el botón de búsqueda en el teclado.
 * @param placeholder Texto de ayuda que se muestra cuando el campo está vacío.
 */
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch : (String) -> Unit,
    placeholder: String
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        placeholder = { Text(placeholder) },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Search")
        },
        trailingIcon = {
            if(query.isNotEmpty()){
                IconButton(onClick = {onQueryChange("")}){
                    Icon(Icons.Default.Close, contentDescription = "Clear")
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {onSearch(query)}
        ),
        singleLine = true,
        shape = RoundedCornerShape(24.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
        )
    )
}

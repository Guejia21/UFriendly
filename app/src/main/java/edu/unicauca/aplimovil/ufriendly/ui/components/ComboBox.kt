package edu.unicauca.aplimovil.ufriendly.ui.components

import android.R.attr.enabled
import android.R.attr.type
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import edu.unicauca.aplimovil.ufriendly.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComboBox(
    options: List<String>,
    label: String,
    placeholder: String = "",
    onValueChange: (String) -> Unit

) {
    // State to manage if the menu is expanded or not
    var expanded by remember { mutableStateOf(false) }
    // State to hold the currently selected option
    var selectedOptionText by remember { mutableStateOf("") }

    // The container for the TextField and the DropdownMenu
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        Column {
            Text(
                text = label,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            // The TextField that displays the current selection and serves as the anchor
            TextField(
                // The "menuAnchor" modifier helps position the menu correctly
                //modifier = Modifier.fillMaxWidth(),
                modifier = Modifier
                    .menuAnchor(MenuAnchorType.PrimaryEditable, true)
                    .fillMaxWidth(),
                value = selectedOptionText,
                placeholder = {Text(placeholder)},
                onValueChange = onValueChange,
                readOnly = true, // Makes the TextField read-only as selections come from the menu
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                )
            )

            // The DropdownMenu that appears when the TextField is clicked
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                // Add each option as a DropdownMenuItem
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false // Collapse the menu after selection
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }
    }
}



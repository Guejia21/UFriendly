package edu.unicauca.aplimovil.ufriendly.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import edu.unicauca.aplimovil.ufriendly.R
import edu.unicauca.aplimovil.ufriendly.data.SaveableItem

/**
 * Un contenedor genérico en forma de tarjeta para agrupar campos de formulario.
 *
 * @param title Título opcional para el grupo de campos.
 * @param itemToSave Objeto que se guardará al presionar el botón.
 * @param addNewItem Acción a realizar con el objeto creado al presionar el botón.
 * @param modifier Modificador para ajustar el diseño externo.
 * @param content Bloque composable que contiene los campos del formulario.
 */
@Composable
fun FormCard(
    modifier: Modifier = Modifier,
    title: String? = null,
    itemToSave: SaveableItem,
    addNewItem: (SaveableItem) -> Unit = {},
    afterSave: () -> Unit = {},
    buttonLabel: String = stringResource(R.string.save_generic_label),
    content: @Composable ColumnScope.() -> Unit = {}
) {
    var save by remember { mutableStateOf(false) }
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.onPrimary
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (title != null) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            content()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    text = buttonLabel,
                    onClick = {
                        addNewItem(itemToSave)
                        afterSave()
                    },
                    enabled = itemToSave.isValid(),
                    isSelected = save,
                    modifier = Modifier.size(width = 200.dp, height = 60.dp)
                )
            }
        }
    }
}

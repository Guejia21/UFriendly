package edu.unicauca.aplimovil.ufriendly.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.unicauca.aplimovil.ufriendly.data.DashboardState
import edu.unicauca.aplimovil.ufriendly.data.Subject
import edu.unicauca.aplimovil.ufriendly.ui.components.BottomBar
import edu.unicauca.aplimovil.ufriendly.ui.components.SubjectDashboard
import edu.unicauca.aplimovil.ufriendly.ui.components.WelcomeDashboard
import edu.unicauca.aplimovil.ufriendly.ui.theme.UFriendlyTheme

@Composable
fun MainScreen(
    state: DashboardState,
    subjects: List<Subject>,
    onAddClick: () -> Unit,
    onViewAllClick: () -> Unit
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        bottomBar = { BottomBar() },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick, shape = MaterialTheme.shapes.extraLarge, containerColor = MaterialTheme.colorScheme.tertiaryContainer) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add new entry"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) //recomendado para dispositivos pequeños donde haya que hacer scroll
        ) {
            WelcomeDashboard(
                state = state
            )
            SubjectDashboard(
                subjects = subjects
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    UFriendlyTheme() {
        MainScreen(
            state = state,
            subjects = subjects,
            onAddClick = {},
            onViewAllClick = {}
        )
    }
}
val state = DashboardState(
    userName = "Jhoan Chacon",
    pendingCount = 5,
    doneCount = 10,
    expiredCount = 2
)
val subjects = listOf(
    Subject("Matemáticas", listOf("Lunes 8-11", "Miércoles 8-11")),
    Subject("Física", listOf("Martes 11-13", "Jueves 12-14")),
    Subject("Química", listOf("Lunes 13-16", "Miércoles 13-16")),
    Subject("Historia", listOf("Martes 16-19", "Jueves 16-19")),
    Subject("Biología", listOf("Lunes 19-22", "Miércoles 19-22")),
    Subject("Literatura", listOf("Martes 22-25", "Jueves 22-25"))
)
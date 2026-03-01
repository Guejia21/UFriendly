package edu.unicauca.aplimovil.ufriendly.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import edu.unicauca.aplimovil.ufriendly.R
import edu.unicauca.aplimovil.ufriendly.data.DashboardState

@Composable
fun WelcomeDashboard(
    state: DashboardState,
    onViewAllClick: () -> Unit = {} // Va para navegabilidad (DESPUÉS)
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "¡Welcome ${state.userName}!",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Inner Dashboard Card
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.surface
            ) {
                Column(modifier = Modifier.padding(vertical = 16.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.task_summary_label),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.titleMedium
                        )
                        TextButton(onClick = onViewAllClick,colors = buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)) {
                            Text(text = stringResource(R.string.button_label),color = MaterialTheme.colorScheme.onTertiaryContainer)
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        // Use weight(1f) so they distribute evenly
                        TaskSummaryCard(
                            quantity = state.pendingCount,
                            title = stringResource(R.string.pending_task),
                            icon = Icons.Rounded.Warning,
                            contentDescription = "Pending tasks",
                            iconColor = Color.Yellow,
                            modifier = Modifier.weight(1f)
                        )
                        TaskSummaryCard(
                            quantity = state.doneCount,
                            title = stringResource(R.string.done_label_task),
                            icon = Icons.Rounded.CheckCircle,
                            contentDescription = "Done tasks",
                            iconColor = Color.Green,
                            modifier = Modifier.weight(1f)
                        )
                        TaskSummaryCard(
                            quantity = state.expiredCount,
                            title = stringResource(R.string.expired_task_label),
                            icon = Icons.Rounded.Close,
                            contentDescription = "Expired tasks",
                            iconColor = Color.Red,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}
package edu.unicauca.aplimovil.ufriendly.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import edu.unicauca.aplimovil.ufriendly.R
import edu.unicauca.aplimovil.ufriendly.ui.nav.ScreenName

@Composable
fun BottomBar(navController: NavHostController) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        IconButton(
            label = stringResource(R.string.home_button_label),
            icon = Icons.Rounded.Home,
            contentDescription = stringResource(R.string.home_button_label),
            onClick = { navController.navigate(ScreenName.Home.name) }
        )
        IconButton(
            label = stringResource(R.string.subject_label),
            icon = Icons.Rounded.MailOutline,
            contentDescription = stringResource(R.string.subject_label),
            onClick = { navController.navigate(ScreenName.SubjectScreen.name) }
        )
        IconButton(
            label = stringResource(R.string.task_label),
            icon = Icons.Rounded.Check,
            contentDescription = stringResource(R.string.task_label),
            onClick = { navController.navigate(ScreenName.TaskScreen.name) })
        IconButton(
            label = stringResource(R.string.grades_label),
            icon = Icons.Rounded.Edit,
            contentDescription = stringResource(R.string.grades_label),
            onClick = { /* Navigate to Grades if exists */ })
    }
}

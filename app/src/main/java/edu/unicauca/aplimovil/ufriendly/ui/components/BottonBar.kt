package edu.unicauca.aplimovil.ufriendly.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Grade
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import edu.unicauca.aplimovil.ufriendly.R
import androidx.compose.foundation.layout.navigationBarsPadding
import edu.unicauca.aplimovil.ufriendly.ui.nav.ScreenName

@Composable
fun BottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .navigationBarsPadding()
    ) {
        IconButton(
            label = stringResource(R.string.home_button_label),
            icon = Icons.Rounded.Home,
            contentDescription = stringResource(R.string.home_button_label),
            onClick = { navController.navigate(ScreenName.Home.name) },
            isSelected = currentRoute == ScreenName.Home.name
        )
        IconButton(
            label = stringResource(R.string.subjects_label),
            icon = Icons.Default.Book,
            contentDescription = stringResource(R.string.subjects_label),
            onClick = { navController.navigate(ScreenName.SubjectScreen.name) },
            isSelected = currentRoute == ScreenName.SubjectScreen.name || currentRoute == ScreenName.AddSubjectScreen.name || currentRoute == ScreenName.SubjectDetailScreen.name
        )
        IconButton(
            label = stringResource(R.string.task_label),
            icon = Icons.Default.TaskAlt,
            contentDescription = stringResource(R.string.task_label),
            onClick = { navController.navigate(ScreenName.TaskScreen.name) },
            isSelected = currentRoute == ScreenName.TaskScreen.name || currentRoute == ScreenName.AddTaskScreen.name || currentRoute == ScreenName.TaskDetailScreen.name
        )
        IconButton(
            label = stringResource(R.string.grades_label),
            icon = Icons.Default.Grade,
            contentDescription = stringResource(R.string.grades_label),
            onClick = { navController.navigate(ScreenName.GradesScreen.name) },
            isSelected = currentRoute == ScreenName.GradesScreen.name || currentRoute == ScreenName.AddGradeScreen.name
        )
        IconButton(
            label = stringResource(R.string.about_us_label),
            icon = Icons.Default.People,
            contentDescription = stringResource(R.string.about_us_label),
            onClick = { navController.navigate(ScreenName.AboutUsScreen.name) },
            isSelected = currentRoute == ScreenName.AboutUsScreen.name
        )

    }
}

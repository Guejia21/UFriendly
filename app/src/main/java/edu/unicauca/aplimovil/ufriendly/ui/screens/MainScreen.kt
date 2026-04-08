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
import androidx.navigation.NavHostController
import edu.unicauca.aplimovil.ufriendly.data.DashboardState
import edu.unicauca.aplimovil.ufriendly.data.entity.Subject
import edu.unicauca.aplimovil.ufriendly.data.relation.SubjectFullInfo
import edu.unicauca.aplimovil.ufriendly.data.relation.SubjectWithSchedules
import edu.unicauca.aplimovil.ufriendly.ui.components.BottomBar
import edu.unicauca.aplimovil.ufriendly.ui.components.SubjectDashboard
import edu.unicauca.aplimovil.ufriendly.ui.components.WelcomeDashboard
import edu.unicauca.aplimovil.ufriendly.ui.nav.ScreenName
import edu.unicauca.aplimovil.ufriendly.ui.theme.UFriendlyTheme

@Composable
fun MainScreen(
    state: DashboardState,
    subjects: List<SubjectFullInfo>,
    navController: NavHostController
) {
    GenericScreen(navController=navController)
    {
        Column {
            WelcomeDashboard(
                state = state,
                onViewAllClick = { navController.navigate(ScreenName.TaskScreen.name) }
            )
            SubjectDashboard(
                subjects = subjects,
                onViewAllClick = { navController.navigate(ScreenName.SubjectScreen.name) }
            )
        }
    }
}
//@Preview(showBackground = true)
//@Composable
//fun MainScreenPreview() {
//    UFriendlyTheme() {
//        MainScreen(
//            state = state,
//            subjects = subjects,
//            navController = rememberNavController()
//        )
//    }
//}

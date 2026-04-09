package edu.unicauca.aplimovil.ufriendly.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import edu.unicauca.aplimovil.ufriendly.R
import edu.unicauca.aplimovil.ufriendly.ui.components.TopBar
import edu.unicauca.aplimovil.ufriendly.ui.theme.UFriendlyTheme


@Composable
fun AboutUsScreen(navController: NavHostController) {
    GenericScreen(
        topBar = { TopBar(stringResource(R.string.credits_label)) },
        navController = navController,
    ) {
        CreditsContent()
    }
}

@Composable
fun CreditsContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        // ── Título de sección ───────────────────────────────
        Text(
            text = stringResource(R.string.development_team_label),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )

        Text(
            text = "The people behind this application",
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )

        // ── Foto grupal de los desarrolladores ─────────────
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(290.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.team_photo),
                contentDescription = "Development team photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                alignment = Alignment.BottomCenter
            )
        }

        DeveloperCard(
            name = "Jonathan David Guejia Burbano",
            role = "Fullstack Developer",
            initials = "JG"
        )

        DeveloperCard(
            name = "Jhoan David Chacón",
            role = "Fullstack Developer",
            initials = "JC"
        )

        ProjectInfoCard()

        Spacer(modifier = Modifier.height(8.dp))
    }
}

// ─────────────────────────────────────────────────────────────
//  Tarjeta de desarrollador individual
// ─────────────────────────────────────────────────────────────
@Composable
fun DeveloperCard(
    name: String,
    role: String,
    initials: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Avatar con iniciales
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape),
                    //.background(AppAccent),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = initials,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    //color = TextPrimary
                )
                Text(
                    text = role,
                    fontSize = 13.sp,
                    //color = TextSecondary
                )
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────
//  Tarjeta de información del proyecto
// ─────────────────────────────────────────────────────────────
@Composable
fun ProjectInfoCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "About the App",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                //color = AppNavy
            )

            InfoRow(label = "App name",    value = "UFriendly")
            InfoRow(label = "Version",     value = "1.0.0")
            InfoRow(label = "University",  value = "Universidad del Cauca")
            InfoRow(label = "Year",        value = "2026")
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 13.sp)
        Text(text = value, fontSize = 13.sp, fontWeight = FontWeight.Medium)
    }
}


@Preview(
    showBackground = true,
)
@Composable
fun CreditsScreenPreview(){
    val navController = NavHostController(LocalContext.current)
    UFriendlyTheme() {
        AboutUsScreen(navController)
    }
}
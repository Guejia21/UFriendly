package edu.unicauca.aplimovil.ufriendly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.unicauca.aplimovil.ufriendly.data.Subject
import edu.unicauca.aplimovil.ufriendly.data.Task
import edu.unicauca.aplimovil.ufriendly.ui.components.BottomBar
import edu.unicauca.aplimovil.ufriendly.ui.screens.AddSubjectScreen
import edu.unicauca.aplimovil.ufriendly.ui.screens.SubjectScreen
import edu.unicauca.aplimovil.ufriendly.ui.screens.TaskScreen
import edu.unicauca.aplimovil.ufriendly.ui.theme.UFriendlyTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val subject = Subject("Cálculo I", listOf("Lunes 8-11"), "Juan Pérez", 80, 2.9, Color(0xFFE8D08A))
            val subject2 = Subject("Programación Avanzada", listOf("Martes 10-12"), "Ana García", 75, 3.5, Color(0xFF90CAF9))
            val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val tasks = listOf(
                Task("Preparar presentación de cálculo", "Desc", today, false, subject),
                Task("Estudiar ondas", "Desc", today, false, subject),
                Task("Proyecto de programación", "Desc", "2023-10-28", false, subject2),
                Task("Proyecto de automatización", "Desc", "2023-10-28", false, subject2)
            )
            UFriendlyTheme {
                TaskScreen(tasks = tasks, onAddClick = {})
            }
        }
    }
}
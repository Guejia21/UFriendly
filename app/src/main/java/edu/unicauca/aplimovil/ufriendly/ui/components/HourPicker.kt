package edu.unicauca.aplimovil.ufriendly.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

/**
 * Selector de hora con desplazamiento suave (LazyColumn).
 * Incluye efectos de snapping y gradientes visuales para resaltar la selección.
 *
 * @param label Título superior del selector.
 * @param hourSelected Hora inicial/seleccionada.
 * @param onSelect Callback con la nueva hora elegida.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HourPicker(
    label: String,
    hourSelected: String,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val hours = listOf(
        "6:00 AM", "6:30 AM", "7:00 AM", "7:30 AM", "8:00 AM", "8:30 AM",
        "9:00 AM", "9:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM",
        "12:00 PM", "12:30 PM", "1:00 PM", "1:30 PM", "2:00 PM", "2:30 PM",
        "3:00 PM", "3:30 PM", "4:00 PM", "4:30 PM", "5:00 PM", "5:30 PM",
        "6:00 PM", "6:30 PM", "7:00 PM", "7:30 PM", "8:00 PM"
    )
    val itemHeight = 48.dp

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = (hours.indexOf(hourSelected)).coerceAtLeast(0)
    )
    
    // Detectar item central
    LaunchedEffect(listState.isScrollInProgress) {
        if (!listState.isScrollInProgress) {
            val index = listState.firstVisibleItemIndex
            if (index in hours.indices) {
                onSelect(hours[index])
            }
        }
    }

    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            letterSpacing = 0.08.em
        )
        Spacer(Modifier.height(6.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeight * 3)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF2F5FC))
                .border(1.5.dp, Color(0xFFDCE6F8), RoundedCornerShape(12.dp))
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = itemHeight),
                flingBehavior = rememberSnapFlingBehavior(listState)
            ) {
                itemsIndexed(hours) { index, hour ->
                    val isSelected = index == listState.firstVisibleItemIndex

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(itemHeight),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = hour,
                            fontSize = if (isSelected) 16.sp else 13.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) MaterialTheme.colorScheme.primary
                            else Color(0xFF9AAACE)
                        )
                    }
                }
            }
            
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .height(itemHeight)
                    .background(Color(0x162547A8))
                    .border(width = Dp.Hairline, color = Color(0x552547A8))
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(itemHeight)
                    .align(Alignment.TopCenter)
                    .background(Brush.verticalGradient(listOf(Color(0xFFF2F5FC), Color.Transparent)))
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(itemHeight)
                    .align(Alignment.BottomCenter)
                    .background(Brush.verticalGradient(listOf(Color.Transparent, Color(0xFFF2F5FC))))
            )
        }
    }
}

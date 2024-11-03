package com.example.composedices.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HistoryScreen(
    history: ArrayList<String>,
    numberOfRolls: Int,
    onClickDices: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Text(text = "Total de lanzamientos: $numberOfRolls")
        }
        item {
            Text(text = "Historial de lanzamientos")
        }
        items(history) { history ->
            HistoryText(history)
        }
        item {
            Button(
                onClick = onClickDices
            ) {
                Text(text = "Volver")
            }
        }
    }
}

@Composable
fun HistoryText(history: String) {
    Card(
        modifier = Modifier.padding(4.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Text(
            text = history,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}
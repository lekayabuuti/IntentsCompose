package br.edu.ifsp.scl.sc3047733.intentscompose.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    currentText: String,
    onAddWordClick: () -> Unit,
    onResetClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "String atual:", style = MaterialTheme.typography.titleMedium)

        OutlinedTextField(
            value = currentText,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = onAddWordClick, modifier = Modifier.fillMaxWidth()) {
            Text("Adicionar palavra")
        }

        OutlinedButton(onClick = onResetClick, modifier = Modifier.fillMaxWidth()) {
            Text("Reiniciar")
        }
    }
}
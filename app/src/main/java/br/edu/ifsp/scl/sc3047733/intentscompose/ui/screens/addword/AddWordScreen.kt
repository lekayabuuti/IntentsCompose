package br.edu.ifsp.scl.sc3047733.intentscompose.ui.screens.addword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddWordScreen(
    currentText: String,
    onConcatenarClick: (novaPalavra: String) -> Unit
) {
    var novaPalavra by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "String recebida (somente leitura):", style = MaterialTheme.typography.titleMedium)

        OutlinedTextField(
            value = currentText,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        Text(text = "Nova palavra:", style = MaterialTheme.typography.titleMedium)

        OutlinedTextField(
            value = novaPalavra,
            onValueChange = {novaPalavra = it },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {onConcatenarClick(novaPalavra)},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Concatenar")
        }
    }
}
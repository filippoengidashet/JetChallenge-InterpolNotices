package com.filippoengidashet.challenge4.lloyds.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ErrorMessageComponent(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message, modifier = Modifier.padding(8.dp))
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ErrorMessageComponentPreview() {
    ErrorMessageComponent("Error message") {
        //handle click
    }
}


package com.filippoengidashet.challenge4.lloyds.ui.popups.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SearchDialog(
    scope: CoroutineScope,
    query: String?,
    onDismiss: (String?) -> Unit
) {

    val bottomSheetState = rememberModalBottomSheetState()
    var searchQuery by remember { mutableStateOf(query.orEmpty()) }

    ModalBottomSheet(
        onDismissRequest = {
            onDismiss.invoke(null)
        },
        sheetState = bottomSheetState,
        properties = ModalBottomSheetDefaults.properties
    ) {

        Column(
            modifier = Modifier.padding(12.dp),
        ) {
            Text(
                text = "Search:",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 15.sp
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                },
                placeholder = { Text("Type name...") }
            )
            Spacer(Modifier.height(12.dp))

            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = {
                    scope.launch {
                        bottomSheetState.hide()
                    }.invokeOnCompletion {
                        if (!bottomSheetState.isVisible) {
                            onDismiss.invoke(searchQuery)
                        }
                    }
                }) {
                Text("Search")
            }
        }
    }
}

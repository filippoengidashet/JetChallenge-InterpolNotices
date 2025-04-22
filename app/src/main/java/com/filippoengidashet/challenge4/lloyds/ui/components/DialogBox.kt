package com.filippoengidashet.challenge4.lloyds.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun DialogBox(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    content: @Composable BoxScope.() -> Unit,
) {

    Dialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        ),
        onDismissRequest = onDismiss,
    ) {

        Box(modifier = modifier.systemBarsPadding()) {
            content()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DialogBoxPreview() {
    DialogBox(
        onDismiss = { },
    ) {
        Box(modifier = Modifier.systemBarsPadding()) {
            Text(text = "This is a dialog box")
        }
    }
}

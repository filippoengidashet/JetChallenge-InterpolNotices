package com.filippoengidashet.challenge4.lloyds.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DetailSectionComponent(
    title: String, subTitle: String? = null, content: @Composable ColumnScope.() -> Unit = {}
) {

    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        subTitle?.let {
            Spacer(Modifier.height(5.dp))
            Text(
                text = it,
                style = MaterialTheme.typography.labelSmall,
                fontStyle = FontStyle.Italic
            )
        }

        content()
    }
}

@Composable
@Preview(showBackground = true)
fun DetailSectionComponentPreview() {
    DetailSectionComponent(
        title = "Header Title",
        subTitle = "Small description under header section"
    ) {
        Text(text = "This is a detail item")
        Text(text = "This is a detail item")
        Text(text = "This is a detail item")
    }
}

package com.filippoengidashet.challenge4.lloyds.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BulletItem(text: String) {
    Row(modifier = Modifier.padding(8.dp)) {
        Text(
            text = "*",
            modifier = Modifier.padding(end = 8.dp),
            style = TextStyle(
                fontSize = 20.sp
            )
        )
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun BulletItemPreview() {
    BulletItem("This is a bullet item")
}

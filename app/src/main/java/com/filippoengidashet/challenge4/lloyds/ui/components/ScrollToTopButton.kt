package com.filippoengidashet.challenge4.lloyds.ui.components

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ScrollToTopButton(onClick: () -> Unit) {

    IconButton(
        modifier = Modifier
            .clip(shape = CircleShape)
            //.systemBarsPadding()
            .navigationBarsPadding()
            .size(48.dp),

        onClick = { onClick() },
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Color.LightGray,
        )
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowUp,
            contentDescription = "Scroll to top",
            tint = Color.Unspecified
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ScrollToTopButtonPreview() {
    ScrollToTopButton {
        //handle click
    }
}

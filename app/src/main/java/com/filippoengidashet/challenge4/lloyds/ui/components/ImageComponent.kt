package com.filippoengidashet.challenge4.lloyds.ui.components

import androidx.compose.foundation.background
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.filippoengidashet.challenge4.lloyds.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageComponent(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {

    if (url.isNotBlank()) {
        GlideImage(
            model = GlideUrl(
                url, LazyHeaders.Builder()
                    .addHeader(
                        "User-Agent",
                        "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/13"
                    )
                    .addHeader("Accept", "*/*")
                    .build()
            ),
            modifier = modifier,
            contentScale = ContentScale.Fit,
            contentDescription = contentDescription,
        )
    } else {
        Icon(
            painter = painterResource(R.drawable.ic_no_image),
            modifier = modifier.background(Color.White),
            contentDescription = "No Image",
            tint = Color.Gray
        )
    }
}

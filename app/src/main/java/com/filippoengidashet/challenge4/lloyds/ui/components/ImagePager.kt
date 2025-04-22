package com.filippoengidashet.challenge4.lloyds.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ImagePager(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    images: List<String>,
) {

    HorizontalPager(
        state = pagerState,
        modifier = modifier
    ) { page ->

        ImageComponent(
            url = images[page],
            modifier = Modifier.fillMaxSize(),
            contentDescription = "Image $page",
        )

    }
}

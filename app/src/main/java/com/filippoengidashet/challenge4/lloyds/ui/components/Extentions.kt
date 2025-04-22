package com.filippoengidashet.challenge4.lloyds.ui.components

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun LazyListState.isScrollingUp(): Boolean {

    var previousIndex by remember(this) {
        mutableIntStateOf(-1)
    }
    var previousScrollOffset by remember(this) {
        mutableIntStateOf(-1)
    }
    return remember(this) {
        derivedStateOf {
            if (firstVisibleItemIndex == 0) {
                return@derivedStateOf false
            }
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}

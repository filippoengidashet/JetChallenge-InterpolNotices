package com.filippoengidashet.challenge4.lloyds.common

import android.content.res.Configuration

/**
 * This function checks if the string is null and falls back to provided alt value
 */
fun String?.orElse(alt: String): String {
    return this ?: alt
}

fun Configuration.isNightMode(): Boolean =
    (uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

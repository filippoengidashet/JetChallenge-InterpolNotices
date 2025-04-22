package com.filippoengidashet.challenge4.lloyds.common

import android.util.Log
import com.filippoengidashet.challenge4.lloyds.BuildConfig
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

object Utils {

    const val TAG = "DEBUG_TAG"
    const val DATE_PATTERN = "yyyy/MM/dd"

    @JvmStatic
    fun log(message: String) {
        //OS Log messages will be disabled in production version
        if (BuildConfig.DEBUG) {
            Log.d(TAG, message)
        }
    }

    @JvmStatic
    fun capitalizeFirstLetter(text: String): String {
        return text.trim().lowercase().split(" ").joinToString(" ") {
            it.replaceFirstChar { char -> char.uppercase() }
        }
    }

    fun calculateAge(dateOfBirth: String): Int {
        try {
            val date = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
                .parse(dateOfBirth)

            val dobCalendar = Calendar.getInstance()
            date?.let(dobCalendar::setTime)

            val currentCalendar = Calendar.getInstance().apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }

            var age = currentCalendar.get(Calendar.YEAR) - dobCalendar.get(Calendar.YEAR)
            if (currentCalendar.get(Calendar.DAY_OF_YEAR) < dobCalendar.get(Calendar.DAY_OF_YEAR)) {
                age--
            }
            return age
        } catch (ignored: Exception) {
            return -1
        }
    }
}

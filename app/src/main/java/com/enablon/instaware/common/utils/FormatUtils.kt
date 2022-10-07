package com.enablon.instaware.common.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Format timestamp into a string of a formatted Date and Time
 */
fun getReadableTimeDate(timestamp: String?): String {
    timestamp?.let {
        val oldDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
        val date = oldDateFormat.parse(it)
        oldDateFormat.applyPattern("EEE, d MMM yyyy 'at' h:mm a")
        date?.let {
            return oldDateFormat.format(it)
        } ?: return ""
    } ?: return ""
}
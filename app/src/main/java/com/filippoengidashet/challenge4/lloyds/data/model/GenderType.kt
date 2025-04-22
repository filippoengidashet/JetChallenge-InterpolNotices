package com.filippoengidashet.challenge4.lloyds.data.model

enum class GenderType(val key: String, val value: String) {

    UNKNOWN("U", "Unknown"),
    MALE("M", "Male"),
    FEMALE("F", "Female"),
    ;

    override fun toString(): String {
        return value
    }

    companion object {
        fun from(key: String): GenderType {
            return entries.find { it.key == key } ?: UNKNOWN
        }
    }
}

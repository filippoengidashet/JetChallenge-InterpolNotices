package com.filippoengidashet.challenge4.lloyds.domain.model

enum class ThemeSettingType(val id: Int) {

    SYSTEM(id = 0),
    LIGHT(id = 1),
    DARK(id = 2),
    ;

    companion object {

        fun from(id: Int?): ThemeSettingType {
            return entries.firstOrNull {
                it.id == id
            } ?: SYSTEM
        }
    }
}

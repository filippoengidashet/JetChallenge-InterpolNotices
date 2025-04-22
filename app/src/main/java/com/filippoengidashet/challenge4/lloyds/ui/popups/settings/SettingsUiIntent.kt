package com.filippoengidashet.challenge4.lloyds.ui.popups.settings

import com.filippoengidashet.challenge4.lloyds.domain.model.ThemeSettingType

sealed class SettingsUiIntent {
    class UpdateTheme(val themeType: ThemeSettingType) : SettingsUiIntent()
}

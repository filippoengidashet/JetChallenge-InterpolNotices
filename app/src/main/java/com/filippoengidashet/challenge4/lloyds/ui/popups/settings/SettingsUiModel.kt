package com.filippoengidashet.challenge4.lloyds.ui.popups.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filippoengidashet.challenge4.lloyds.domain.model.StorageDao
import com.filippoengidashet.challenge4.lloyds.domain.model.ThemeSettingType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsUiModel @Inject constructor(
    private val storageDao: StorageDao
) : ViewModel() {

    val themeSetting = storageDao.getSavedThemeSetting()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ThemeSettingType.SYSTEM
        )

    fun handleIntent(intent: SettingsUiIntent) {
        when (intent) {
            is SettingsUiIntent.UpdateTheme -> {
                saveThemeSetting(intent)
            }
        }
    }

    private fun saveThemeSetting(intent: SettingsUiIntent.UpdateTheme) {
        viewModelScope.launch {
            storageDao.saveThemeSetting(intent.themeType)
        }
    }
}

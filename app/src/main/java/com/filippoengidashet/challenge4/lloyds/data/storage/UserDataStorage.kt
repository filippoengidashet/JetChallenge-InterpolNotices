package com.filippoengidashet.challenge4.lloyds.data.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.filippoengidashet.challenge4.lloyds.domain.model.CountryCode
import com.filippoengidashet.challenge4.lloyds.domain.model.StorageDao
import com.filippoengidashet.challenge4.lloyds.domain.model.ThemeSettingType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataStorage @Inject constructor(
    private val context: Context
) : StorageDao {

    private val gson = Gson()

    private val Context.dataStore by preferencesDataStore(name = "user_data")

    override fun getSavedThemeSetting(): Flow<ThemeSettingType> {
        return context.dataStore.data
            .map { settings ->
                ThemeSettingType.Companion.from(settings[KEY_THEME])
            }
    }

    override suspend fun saveThemeSetting(type: ThemeSettingType) {
        context.dataStore.edit { settings ->
            settings[KEY_THEME] = type.id
        }
    }

    override suspend fun removeThemeSetting() {
        context.dataStore.edit { settings ->
            settings.remove(KEY_THEME)
        }
    }

    override suspend fun getAvailableCountryCodes(): List<CountryCode> {
        val pref = context.dataStore.data.first()
        val json = pref[KEY_COUNTRY_CODES] ?: return emptyList()
        val type = object : TypeToken<List<CountryCode>>() {}.type
        return gson.fromJson<List<CountryCode>>(json, type)
    }

    override suspend fun setCountryCodes(codes: List<CountryCode>) {
        val json = gson.toJson(codes)
        context.dataStore.edit { settings ->
            settings[KEY_COUNTRY_CODES] = json
        }
    }

    override suspend fun clearAll() {
        context.dataStore.edit {
            it.clear()
        }
    }

    companion object {

        val KEY_THEME = intPreferencesKey("preference.key.theme")
        val KEY_COUNTRY_CODES = stringPreferencesKey("preference.key.country_codes")
    }
}

package com.filippoengidashet.challenge4.lloyds.domain.model

import kotlinx.coroutines.flow.Flow

interface StorageDao {

    fun getSavedThemeSetting(): Flow<ThemeSettingType>

    suspend fun saveThemeSetting(type: ThemeSettingType)

    suspend fun removeThemeSetting()

    suspend fun getAvailableCountryCodes() : List<CountryCode>

    suspend fun setCountryCodes(codes: List<CountryCode>)

    suspend fun clearAll()
}

package com.filippoengidashet.challenge4.lloyds.domain.repository

import com.filippoengidashet.challenge4.lloyds.data.model.ResultHolder
import com.filippoengidashet.challenge4.lloyds.domain.model.CountryCode
import kotlinx.coroutines.flow.Flow

interface CountryCodesRepository {

    suspend fun getCountryCodes(): Flow<ResultHolder<List<CountryCode>>>
}

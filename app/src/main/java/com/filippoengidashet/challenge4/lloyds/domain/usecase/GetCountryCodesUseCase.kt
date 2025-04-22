package com.filippoengidashet.challenge4.lloyds.domain.usecase

import com.filippoengidashet.challenge4.lloyds.data.model.ResultHolder
import com.filippoengidashet.challenge4.lloyds.domain.model.CountryCode
import com.filippoengidashet.challenge4.lloyds.domain.repository.CountryCodesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCountryCodesUseCase @Inject constructor(
    private val repository: CountryCodesRepository
) {

    suspend operator fun invoke(): Flow<ResultHolder<List<CountryCode>>> =
        repository.getCountryCodes()
}

package com.filippoengidashet.challenge4.lloyds.data.repository

import com.filippoengidashet.challenge4.lloyds.data.mapper.toDomain
import com.filippoengidashet.challenge4.lloyds.data.model.ResultHolder
import com.filippoengidashet.challenge4.lloyds.data.service.CountryCodesApiService
import com.filippoengidashet.challenge4.lloyds.domain.model.CountryCode
import com.filippoengidashet.challenge4.lloyds.domain.model.StorageDao
import com.filippoengidashet.challenge4.lloyds.domain.repository.CountryCodesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CountryCodesRepositoryImpl(
    private val storageDao: StorageDao,//Local/Cached data
    private val apiService: CountryCodesApiService,//Remote data
) : CountryCodesRepository {

    override suspend fun getCountryCodes(): Flow<ResultHolder<List<CountryCode>>> {
        return flow {

            storageDao.getAvailableCountryCodes().takeIf {
                it.isNotEmpty()
            }?.let {
                emit(ResultHolder.Success(it))
            }

            try {

                val fetchedCountryCodes = apiService.getCountryCodes()

                if (fetchedCountryCodes.isNotEmpty()) {

                    val newCountryCodes = fetchedCountryCodes.map {
                        it.toDomain()
                    }

                    storageDao.setCountryCodes(newCountryCodes)

                    val result = ResultHolder.Success(newCountryCodes)
                    emit(result)
                }

            } catch (e: Exception) {
                val failure = ResultHolder.Failure(e)
                emit(failure)
            }
        }.flowOn(Dispatchers.IO)
    }
}

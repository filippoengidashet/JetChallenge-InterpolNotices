package com.filippoengidashet.challenge4.lloyds.data.repository

import com.filippoengidashet.challenge4.lloyds.data.model.CountryCodeDto
import com.filippoengidashet.challenge4.lloyds.data.model.ResultHolder
import com.filippoengidashet.challenge4.lloyds.data.service.CountryCodesApiService
import com.filippoengidashet.challenge4.lloyds.domain.model.CountryCode
import com.filippoengidashet.challenge4.lloyds.domain.model.StorageDao
import com.filippoengidashet.challenge4.lloyds.domain.repository.CountryCodesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

//Class tested with MockK
class CountryCodesRepositoryTest {

    private lateinit var repository: CountryCodesRepository

    private val storageDao = mockk<StorageDao>(relaxed = true)
    private val apiService = mockk<CountryCodesApiService>(relaxed = true)

    @Before
    fun setUp() {
        repository = CountryCodesRepositoryImpl(
            apiService = apiService, storageDao = storageDao
        )
    }

    @Test
    fun `test_country_codes_when_not_cached_and_api_returns_empty_success`() = runTest {

        //GIVEN
        coEvery { storageDao.getAvailableCountryCodes() } returns emptyList()
        coEvery { apiService.getCountryCodes() } returns emptyList()

        //WHEN
        val collectedResults = mutableListOf<ResultHolder<List<CountryCode>>>()
        repository.getCountryCodes().collect { result ->
            collectedResults.add(result)
        }

        //THEN
        assertEquals(0, collectedResults.size)
    }

    @Test
    fun `test_country_codes_when_not_cached_and_api_returns_success_then_cache_is_updated`() {
        runTest {

            //GIVEN
            val sampleCountryCodes = listOf(
                CountryCodeDto("name1", "code1"),
                CountryCodeDto("name2", "code2"),
                CountryCodeDto("name3", "code3")
            )

            coEvery { storageDao.getAvailableCountryCodes() } returns emptyList()
            coEvery { apiService.getCountryCodes() } returns sampleCountryCodes

            //WHEN
            val collectedResults = mutableListOf<ResultHolder<List<CountryCode>>>()
            repository.getCountryCodes().collect { result ->
                collectedResults.add(result)
            }

            //THEN
            assertEquals(1, collectedResults.size)
            coVerify { storageDao.setCountryCodes(any()) }
        }
    }

    @Test
    fun `test_country_codes_when_not_cached_and_api_returns_failure`() = runTest {

        val errorMessage = "API error"

        //GIVEN
        coEvery { storageDao.getAvailableCountryCodes() } returns emptyList()
        coEvery { apiService.getCountryCodes() } throws Exception(errorMessage)

        //WHEN
        val collectedResults = mutableListOf<ResultHolder<List<CountryCode>>>()
        repository.getCountryCodes().collect { result ->
            collectedResults.add(result)
        }

        //THEN
        assertEquals(1, collectedResults.size)
        assertTrue((collectedResults[0] as ResultHolder.Failure).error.message == errorMessage)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}

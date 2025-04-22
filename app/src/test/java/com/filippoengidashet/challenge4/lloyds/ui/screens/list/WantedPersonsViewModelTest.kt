package com.filippoengidashet.challenge4.lloyds.ui.screens.list

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WantedPersonsViewModelTest {

//    private lateinit var viewModel: WantedPeopleViewModel
//    private lateinit var useCase: GetWantedPeopleUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
//        useCase = mock(GetWantedPeopleUseCase::class.java)
//        viewModel = WantedPeopleViewModel(useCase)
    }

    @Test
    fun `test sample`() = runTest {

//        //Given
//        val results = WantedPeopleResults(total = 500, page = 1, items = listOf())
//
//        `when`(useCase.invoke(WantedPeopleRequestParams())).thenReturn(results)
//
//        //When
//        viewModel.handleIntent(WantedPeopleUiIntent.LoadAction)
//
//        //Then
//        viewModel.uiState.collect {
//            assert(it.isLoading)
//        }
    }

    @Test
    fun handleIntent() {
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}

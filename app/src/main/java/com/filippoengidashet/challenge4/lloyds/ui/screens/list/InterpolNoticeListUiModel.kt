package com.filippoengidashet.challenge4.lloyds.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.filippoengidashet.challenge4.lloyds.data.model.ResultHolder
import com.filippoengidashet.challenge4.lloyds.domain.usecase.GetCountryCodesUseCase
import com.filippoengidashet.challenge4.lloyds.domain.usecase.GetInterpolNoticesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InterpolNoticeListUiModel @Inject constructor(
    private val getNoticesUseCase: GetInterpolNoticesUseCase,
    private val getCountryCodesUseCase: GetCountryCodesUseCase,
) : ViewModel() {

    private val _countryListState = MutableStateFlow(CountryListUiState())
    val countryListState: StateFlow<CountryListUiState> = _countryListState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    val pagingData = _searchQuery
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            Pager(
                config = PagingConfig(
                    pageSize = InterpolNoticesPagingSource.PAGE_OFFSET,
                    initialLoadSize = InterpolNoticesPagingSource.PAGE_OFFSET,
                    prefetchDistance = InterpolNoticesPagingSource.PREFETCH_DISTANCE,
                    enablePlaceholders = false,
                ),
                pagingSourceFactory = {
                    InterpolNoticesPagingSource(getNoticesUseCase, query)
                }
            ).flow
        }
        .cachedIn(viewModelScope) // Cache the flow to preserve the state across configurations

    private var hasLoaded = false

//    init {
//        loadCountryCodes()
//    }

    fun handleIntent(intent: InterpolNoticeListUiIntent) {
        when (intent) {
            InterpolNoticeListUiIntent.LoadCountriesAction -> {
                if (!hasLoaded) {
                    loadCountryCodes()
                }
            }

            InterpolNoticeListUiIntent.ReLoadCountriesAction -> {
                loadCountryCodes()
            }

            is InterpolNoticeListUiIntent.UpdateSearchQuery -> {
                _searchQuery.value = intent.query
            }
        }
    }

    private fun loadCountryCodes() {
        viewModelScope.launch {

            _countryListState.value = _countryListState.value.copy(isLoading = true)

            getCountryCodesUseCase.invoke().collectLatest { result ->
                when (result) {
                    is ResultHolder.Success -> {
                        hasLoaded = true
                        val countryCodeMap = result.value.associateBy { it.code }
                        _countryListState.value = CountryListUiState(itemMap = countryCodeMap)
                    }

                    is ResultHolder.Failure -> {
                        if (!hasLoaded) { //Show retry snackbar only if it hasn't any success load
                            _countryListState.value = _countryListState.value.copy(
                                isLoading = false,
                                error = ErrorState(message = result.error.message)
                            )
                        }
                    }
                }
            }
        }
    }

    // Map over nationalities to get matching CountryCode, filtering out nulls
    fun getCountries(nationalities: List<String>) = nationalities.mapNotNull { nationalityCode ->
        countryListState.value.itemMap[nationalityCode]
    }
}

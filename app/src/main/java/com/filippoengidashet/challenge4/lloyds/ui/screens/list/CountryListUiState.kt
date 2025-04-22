package com.filippoengidashet.challenge4.lloyds.ui.screens.list

import com.filippoengidashet.challenge4.lloyds.domain.model.CountryCode

data class CountryListUiState(
    val isLoading: Boolean = false,
    val itemMap: Map<String, CountryCode> = emptyMap(),
    val error: ErrorState? = null,
)

data class ErrorState(
    val message: String?, //"Something went wrong"
    val id: Long = System.currentTimeMillis()
)

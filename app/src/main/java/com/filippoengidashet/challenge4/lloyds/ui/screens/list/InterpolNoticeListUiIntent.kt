package com.filippoengidashet.challenge4.lloyds.ui.screens.list

sealed interface InterpolNoticeListUiIntent {
    object LoadCountriesAction : InterpolNoticeListUiIntent
    object ReLoadCountriesAction : InterpolNoticeListUiIntent
    class UpdateSearchQuery(val query: String) : InterpolNoticeListUiIntent
}

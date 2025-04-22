package com.filippoengidashet.challenge4.lloyds.ui.screens.details

sealed interface InterpolNoticeDetailUiIntent {

    class LoadAction(val entityId: String) : InterpolNoticeDetailUiIntent
    class ReLoadAction(val entityId: String) : InterpolNoticeDetailUiIntent
}

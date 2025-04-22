package com.filippoengidashet.challenge4.lloyds.ui.screens.details

import com.filippoengidashet.challenge4.lloyds.domain.model.InterpolNotice
import com.filippoengidashet.challenge4.lloyds.domain.model.InterpolNoticeImage

data class InterpolNoticeDetailUiState(
    val isLoading: Boolean = false,
    val notice: InterpolNotice? = null,
    val images: List<InterpolNoticeImage>? = null,
    val error: ErrorState? = null,
)

data class ErrorState(
    val message: String?, //"Something went wrong"
    val id: Long = System.currentTimeMillis()
)

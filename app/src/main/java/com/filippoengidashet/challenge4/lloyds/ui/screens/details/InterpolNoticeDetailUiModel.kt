package com.filippoengidashet.challenge4.lloyds.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filippoengidashet.challenge4.lloyds.common.Utils
import com.filippoengidashet.challenge4.lloyds.domain.usecase.GetInterpolNoticeImagesUseCase
import com.filippoengidashet.challenge4.lloyds.domain.usecase.GetInterpolNoticeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InterpolNoticeDetailUiModel @Inject constructor(
    private val getNoticeUseCase: GetInterpolNoticeUseCase,
    private val getNoticeImagesUseCase: GetInterpolNoticeImagesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(InterpolNoticeDetailUiState())
    val uiState: StateFlow<InterpolNoticeDetailUiState> = _uiState.asStateFlow()

    private var hasLoaded = false

    fun handleIntent(intent: InterpolNoticeDetailUiIntent) {
        when (intent) {
            is InterpolNoticeDetailUiIntent.LoadAction -> {
                if (!hasLoaded) {
                    loadDetail(intent.entityId)
                    hasLoaded = true
                }
            }

            is InterpolNoticeDetailUiIntent.ReLoadAction -> {
                loadDetail(intent.entityId)
            }
        }
    }

    private fun loadDetail(id: String) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Utils.log("Error: ${throwable.message}")
        }
        viewModelScope.launch(coroutineExceptionHandler) {
            _uiState.value = _uiState.value.copy(isLoading = true)
            //delay(Random.Default.nextLong(2000)) //Simulate network delay
            try {
                val fetchNotice = async { getNoticeUseCase.invoke(id) }
                val fetchImages = async { getNoticeImagesUseCase.invoke(id) }
                _uiState.value = InterpolNoticeDetailUiState(
                    notice = fetchNotice.await(),
                    images = fetchImages.await().images,
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = ErrorState(message = e.message)
                )
            }
        }
    }
}

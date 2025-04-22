package com.filippoengidashet.challenge4.lloyds.domain.usecase

import com.filippoengidashet.challenge4.lloyds.domain.model.InterpolNoticeImages
import com.filippoengidashet.challenge4.lloyds.domain.repository.InterpolNoticesRepository
import javax.inject.Inject

class GetInterpolNoticeImagesUseCase @Inject constructor(
    private val repository: InterpolNoticesRepository
) {

    suspend operator fun invoke(entityId: String): InterpolNoticeImages {
        return repository.getInterpolNoticeImages(entityId)
    }
}

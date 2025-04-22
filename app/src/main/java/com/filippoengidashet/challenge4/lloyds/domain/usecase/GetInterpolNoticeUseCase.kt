package com.filippoengidashet.challenge4.lloyds.domain.usecase

import com.filippoengidashet.challenge4.lloyds.domain.repository.InterpolNoticesRepository
import javax.inject.Inject

class GetInterpolNoticeUseCase @Inject constructor(
    private val repository: InterpolNoticesRepository
) {
    suspend operator fun invoke(id: String) = repository.getInterpolNotice(id)
}

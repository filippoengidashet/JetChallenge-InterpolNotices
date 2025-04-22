package com.filippoengidashet.challenge4.lloyds.domain.usecase

import com.filippoengidashet.challenge4.lloyds.domain.model.InterpolNotices
import com.filippoengidashet.challenge4.lloyds.domain.model.RedNoticesParams
import com.filippoengidashet.challenge4.lloyds.domain.repository.InterpolNoticesRepository
import javax.inject.Inject

class GetInterpolNoticesUseCase @Inject constructor(
    private val repository: InterpolNoticesRepository
) {

    suspend operator fun invoke(params: RedNoticesParams): InterpolNotices {
        return repository.getInterpolNotices(params)
    }
}

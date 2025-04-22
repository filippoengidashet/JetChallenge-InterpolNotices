package com.filippoengidashet.challenge4.lloyds.data.repository

import com.filippoengidashet.challenge4.lloyds.data.mapper.toDomain
import com.filippoengidashet.challenge4.lloyds.data.service.InterpolNoticesApiService
import com.filippoengidashet.challenge4.lloyds.domain.model.InterpolNotice
import com.filippoengidashet.challenge4.lloyds.domain.model.InterpolNoticeImages
import com.filippoengidashet.challenge4.lloyds.domain.model.InterpolNotices
import com.filippoengidashet.challenge4.lloyds.domain.model.RedNoticesParams
import com.filippoengidashet.challenge4.lloyds.domain.repository.InterpolNoticesRepository

class InterpolNoticesRepositoryImpl(
    private val apiService: InterpolNoticesApiService,
) : InterpolNoticesRepository {

    override suspend fun getInterpolNotices(params: RedNoticesParams): InterpolNotices {
        return apiService.getInterpolNotices(
            page = params.page,
            offset = params.offset,
            name = params.name,
            forename = params.forename,
            nationality = params.nationality,
            gender = params.gender,
            ageMin = params.ageMin,
            ageMax = params.ageMax,
            wantedBy = params.wantedBy,
            keyword = params.keyword
        ).toDomain()
    }

    override suspend fun getInterpolNotice(entityId: String): InterpolNotice {
        return apiService.getInterpolNotice(entityId).toDomain()
    }

    override suspend fun getInterpolNoticeImages(entityId: String): InterpolNoticeImages {
        return apiService.getInterpolNoticeImages(entityId).toDomain()
    }
}

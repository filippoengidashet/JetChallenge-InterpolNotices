package com.filippoengidashet.challenge4.lloyds.domain.repository

import com.filippoengidashet.challenge4.lloyds.domain.model.InterpolNotice
import com.filippoengidashet.challenge4.lloyds.domain.model.InterpolNoticeImages
import com.filippoengidashet.challenge4.lloyds.domain.model.InterpolNotices
import com.filippoengidashet.challenge4.lloyds.domain.model.RedNoticesParams

interface InterpolNoticesRepository {

    suspend fun getInterpolNotices(params: RedNoticesParams): InterpolNotices

    suspend fun getInterpolNotice(entityId: String): InterpolNotice

    suspend fun getInterpolNoticeImages(entityId: String): InterpolNoticeImages
}

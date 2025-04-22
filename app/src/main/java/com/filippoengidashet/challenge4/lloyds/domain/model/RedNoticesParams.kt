package com.filippoengidashet.challenge4.lloyds.domain.model

import com.filippoengidashet.challenge4.lloyds.data.model.GenderType

data class RedNoticesParams(
    val page: Int? = null,
    val offset: Int? = null,
    val name: String? = null,
    val forename: String? = null,
    val nationality: String? = null,
    val gender: GenderType? = null,
    val ageMin: Int? = null,
    val ageMax: Int? = null,
    val wantedBy: String? = null,
    val keyword: String? = null,
)

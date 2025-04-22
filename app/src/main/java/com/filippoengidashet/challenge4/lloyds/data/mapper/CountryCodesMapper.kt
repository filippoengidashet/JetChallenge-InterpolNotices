package com.filippoengidashet.challenge4.lloyds.data.mapper

import com.filippoengidashet.challenge4.lloyds.data.model.CountryCodeDto
import com.filippoengidashet.challenge4.lloyds.domain.model.CountryCode

fun CountryCodeDto.toDomain(): CountryCode = CountryCode(
    code = value.orEmpty(),
    name = name.orEmpty(),
)

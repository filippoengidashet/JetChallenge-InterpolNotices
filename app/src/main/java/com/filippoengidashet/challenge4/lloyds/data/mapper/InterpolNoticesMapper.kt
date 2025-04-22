package com.filippoengidashet.challenge4.lloyds.data.mapper

import com.filippoengidashet.challenge4.lloyds.common.Utils
import com.filippoengidashet.challenge4.lloyds.common.orElse
import com.filippoengidashet.challenge4.lloyds.data.model.InterpolNoticeImageDto
import com.filippoengidashet.challenge4.lloyds.data.model.InterpolNoticeImagesResponseDto
import com.filippoengidashet.challenge4.lloyds.data.model.InterpolNoticesNoticeDto
import com.filippoengidashet.challenge4.lloyds.data.model.InterpolNoticesResponseDto
import com.filippoengidashet.challenge4.lloyds.domain.model.InterpolNotice
import com.filippoengidashet.challenge4.lloyds.domain.model.InterpolNoticeImage
import com.filippoengidashet.challenge4.lloyds.domain.model.InterpolNoticeImages
import com.filippoengidashet.challenge4.lloyds.domain.model.InterpolNotices

fun InterpolNoticesResponseDto.toDomain(): InterpolNotices = InterpolNotices(
    total = total,
    page = query.page,
    resultPerPage = query.resultPerPage,
    items = _embedded?.notices?.map { it.toDomain() }.orEmpty(),
)

fun InterpolNoticesNoticeDto.toDomain(): InterpolNotice = InterpolNotice(
    name = name.orElse(""),
    forename = forename.orElse(""),
    entity_id = entity_id.orElse(""),
    date_of_birth = date_of_birth.orElse(""),
    age = Utils.calculateAge(date_of_birth.orElse("")),
    nationalities = nationalities.orEmpty(),
    self_href = _links?.self?.href.orElse(""),
    images_href = _links?.images?.href.orElse(""),
    thumbnail_href = _links?.thumbnail?.href.orElse(""),

    //Additional detail fields
    gender = sex_id.orElse(""),
    weight = weight.orElse(""),
    height = height.orElse(""),
    hair_colors = hairs_id.orEmpty(),
    eyes_colors = eyes_colors_id.orEmpty(),
    distinguishing_marks = distinguishing_marks.orElse(""),
    arrest_warrants = arrest_warrants?.map { it.charge.orElse("") }.orEmpty(),
    place_of_birth = place_of_birth.orElse(""),
    country_of_birth_id = country_of_birth_id.orElse(""),
    languages_spoken_ids = languages_spoken_ids.orEmpty(),
)

fun InterpolNoticeImagesResponseDto.toDomain(): InterpolNoticeImages = InterpolNoticeImages(
    images = _embedded?.images?.map { it.toDomain() }.orEmpty(),
)

fun InterpolNoticeImageDto.toDomain(): InterpolNoticeImage = InterpolNoticeImage(
    id = picture_id.orElse(""),
    url = _links?.self?.href.orElse(""),
)

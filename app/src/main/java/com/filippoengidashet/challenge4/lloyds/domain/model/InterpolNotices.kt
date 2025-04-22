package com.filippoengidashet.challenge4.lloyds.domain.model

import kotlin.collections.orEmpty

data class InterpolNotices(
    val total: Int,
    val page: Int,
    val resultPerPage: Int,
    val items: List<InterpolNotice>
)

data class InterpolNotice(
    val name: String,
    val forename: String,
    val entity_id: String,
    val date_of_birth: String,
    val age: Int,
    val nationalities: List<String>,
    val self_href: String,
    val images_href: String,
    val thumbnail_href: String,

    //Additional detail fields
    val gender: String,
    val weight: String,
    val height: String,
    val hair_colors: List<String>,
    val eyes_colors: List<String>,
    val place_of_birth: String,
    val country_of_birth_id: String,
    val distinguishing_marks: String,
    val arrest_warrants: List<String>,
    val languages_spoken_ids: List<String>,
)

data class InterpolNoticeImages(
    val images: List<InterpolNoticeImage>,
)

data class InterpolNoticeImage(
    val id: String,
    val url: String,
)

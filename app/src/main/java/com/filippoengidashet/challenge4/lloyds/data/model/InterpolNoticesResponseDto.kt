package com.filippoengidashet.challenge4.lloyds.data.model

data class InterpolNoticesResponseDto(
    val total: Int,
    val query: InterpolNoticesQueryDto,
    val _embedded: InterpolNoticesEmbeddedDto?,
    val _links: InterpolNoticesLinksDto?
)

data class InterpolNoticesQueryDto(
    val page: Int,
    val resultPerPage: Int,
    val nationality: String?
)

data class InterpolNoticesEmbeddedDto(
    val notices: List<InterpolNoticesNoticeDto>
)

data class InterpolNoticesNoticeDto(
    val date_of_birth: String?,
    val nationalities: List<String>?,
    val forename: String?,
    val entity_id: String?,
    val name: String?,
    val _links: InterpolNoticesLinksDto?,

    //Additional detail fields
    val weight: String?,
    val height: String?,
    val distinguishing_marks: String?,
    val arrest_warrants: List<InterpolNoticesWarrantsDto>?,
    val eyes_colors_id: List<String>?,
    val place_of_birth: String?,
    val sex_id: String?,
    val country_of_birth_id: String?,
    val hairs_id: List<String>?,
    val languages_spoken_ids: List<String>?,
    val _embedded: Any?,
)

data class InterpolNoticesWarrantsDto(
    val charge: String?,
    val issuing_country_id: String?,
)

data class InterpolNoticesLinksDto(
    val self: InterpolNoticesLinkDto?,
    val images: InterpolNoticesLinkDto?,
    val notice: InterpolNoticesLinkDto?,
    val thumbnail: InterpolNoticesLinkDto?
)

data class InterpolNoticesLinkDto(
    val href: String,
)

data class InterpolNoticeImagesResponseDto(
    val _embedded: InterpolNoticeImagesDto?,
    val _links: InterpolNoticesLinksDto?,
)

data class InterpolNoticeImagesDto(
    val images: List<InterpolNoticeImageDto>?,
)

data class InterpolNoticeImageDto(
    val picture_id: String,
    val _links: InterpolNoticesLinksDto?,
)

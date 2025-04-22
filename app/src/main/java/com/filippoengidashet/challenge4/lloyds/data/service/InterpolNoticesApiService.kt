package com.filippoengidashet.challenge4.lloyds.data.service

import com.filippoengidashet.challenge4.lloyds.data.model.GenderType
import com.filippoengidashet.challenge4.lloyds.data.model.InterpolNoticeImagesResponseDto
import com.filippoengidashet.challenge4.lloyds.data.model.InterpolNoticesNoticeDto
import com.filippoengidashet.challenge4.lloyds.data.model.InterpolNoticesResponseDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface InterpolNoticesApiService {

    /**
     * Get list of red notices. A Red Notice is an international alert for a wanted person,
     * but it is not an arrest warrant.
     */
    @Headers(
        "User-Agent: Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/135.0.0.0 Mobile Safari/537.36",
        "Accept: */*",
    )
    @GET("notices/v1/red")
    suspend fun getInterpolNotices(
        @Query("page") page: Int? = null,
        @Query("resultPerPage") offset: Int? = null,
        @Query("name") name: String? = null,
        @Query("forename") forename: String? = null,
        @Query("nationality") nationality: String? = null,
        @Query("sexId") gender: GenderType? = null,
        @Query("ageMin") ageMin: Int? = null,
        @Query("ageMax") ageMax: Int? = null,
        @Query("arrestWarrantCountryId") wantedBy: String? = null,
        @Query("freeText") keyword: String? = null,
    ): InterpolNoticesResponseDto

    @Headers(
        "User-Agent: Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/135.0.0.0 Mobile Safari/537.36",
        "Accept: */*",
    )
    @GET("notices/v1/red/{entityId}")
    suspend fun getInterpolNotice(
        @Path("entityId") entityId: String
    ): InterpolNoticesNoticeDto

    @Headers(
        "User-Agent: Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/135.0.0.0 Mobile Safari/537.36",
        "Accept: */*",
    )
    @GET("notices/v1/red/{entityId}/images")
    suspend fun getInterpolNoticeImages(
        @Path("entityId") entityId: String
    ): InterpolNoticeImagesResponseDto

    @GET("notices/v1/red/{entityId}/images")
    suspend fun getCountryCodes(
        @Path("entityId") entityId: String
    )

    /**
     * Get list of yellow notices. Yellow Notices are issued to locate missing people.
     * Example: minors, vulnerable, etc.
     */
    @GET("notices/v1/yellow")
    suspend fun getYellowNotices(
        @Query("page") page: Int? = null,
        @Query("resultPerPage") pageSize: Int? = null,
        @Query("name") name: String? = null,
        @Query("forename") forename: String? = null,
        @Query("nationality") nationality: String? = null,
        @Query("sexId") gender: GenderType? = null,
        @Query("ageMin") ageMin: Int? = null,
        @Query("ageMax") ageMax: Int? = null,
        @Query("arrestWarrantCountryId") wantedBy: String? = null,
        @Query("freeText") keyword: String? = null,
    ): InterpolNoticesResponseDto
}

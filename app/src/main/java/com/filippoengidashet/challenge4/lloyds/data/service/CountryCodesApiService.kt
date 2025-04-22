package com.filippoengidashet.challenge4.lloyds.data.service

import com.filippoengidashet.challenge4.lloyds.data.model.CountryCodeDto
import retrofit2.http.GET
import retrofit2.http.Headers

interface CountryCodesApiService {

    //https://www.interpol.int/notices/data/countries

    @Headers(
        "User-Agent: Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/135.0.0.0 Mobile Safari/537.36",
        "Accept: */*",
    )
    @GET("notices/data/countries")
    suspend fun getCountryCodes(): List<CountryCodeDto>
}

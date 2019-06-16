package com.charlesmuchene.kotlin.learn.data

import com.charlesmuchene.kotlin.learn.models.Country
import retrofit2.Response
import retrofit2.http.GET

/**
 * Api service
 */
interface ApiService {

    @GET("all")
    suspend fun getCountries(): Response<List<Country>>

}
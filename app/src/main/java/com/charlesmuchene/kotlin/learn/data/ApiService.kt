package com.charlesmuchene.kotlin.learn.data

import com.charlesmuchene.kotlin.learn.models.Country
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("all")
    fun getAllCountries(): Single<Response<List<Country>>>

}
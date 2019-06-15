package com.charlesmuchene.kotlin.learn.utilities

import com.charlesmuchene.kotlin.learn.data.ApiService
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

object Configuration {

    val apiService: ApiService
        get() = Retrofit.Builder()
            .baseUrl(API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    fun initialize() {
        Timber.plant(Timber.DebugTree())
    }
}
package com.charlesmuchene.kotlin.learn.utilities

import android.content.Context
import androidx.room.Room
import com.charlesmuchene.kotlin.learn.data.ApiService
import com.charlesmuchene.kotlin.learn.db.Database
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

object Configuration {

    val apiService: ApiService
        get() = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    lateinit var db: Database
        private set

    fun initialize(context: Context) {
        Timber.plant(Timber.DebugTree())
        db = Room.databaseBuilder(context, Database::class.java, DATABASE_NAME).build()
    }

}
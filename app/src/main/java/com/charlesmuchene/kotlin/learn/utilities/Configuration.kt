package com.charlesmuchene.kotlin.learn.utilities

import android.app.Application
import androidx.room.Room
import com.charlesmuchene.kotlin.learn.data.ApiService
import com.charlesmuchene.kotlin.learn.db.Database
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

/**
 * Configuration class
 */
object Configuration {

    private lateinit var application: Application

    val apiService: ApiService
        get() = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    val db by lazy {
        Room.databaseBuilder(application, Database::class.java, DATABASE_NAME).build()
    }

    /**
     * Initialize configuration
     *
     * @param application [Application] instance
     */
    fun initialize(application: Application) {
        Timber.plant(Timber.DebugTree())
        this.application = application
    }

}
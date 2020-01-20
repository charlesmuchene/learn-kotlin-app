package com.charlesmuchene.kotlin.learn.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.charlesmuchene.kotlin.learn.models.Country

/**
 * App's db
 */
@Database(entities = [Country::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {

    /**
     * Country dao
     */
    abstract fun countryDao(): CountryDao
}
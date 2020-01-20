package com.charlesmuchene.kotlin.learn.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.charlesmuchene.kotlin.learn.models.Country

@Dao
interface CountryDao {

    @Query("SELECT * FROM country")
    fun getAll(): List<Country>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(countries: List<Country>)

}
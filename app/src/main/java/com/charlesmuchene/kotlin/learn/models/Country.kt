package com.charlesmuchene.kotlin.learn.models

import android.os.Parcelable
import com.charlesmuchene.kotlin.learn.utilities.orDash
import kotlinx.android.parcel.Parcelize

/**
 * Country class
 */
@Parcelize
data class Country(
    val name: String, val capital: String, val region: String, val subregion: String, val flag: String,
    val nativeName: String, val population: Int, val demonym: String, val timezones: Array<String>,
    val currencies: Array<Currency>, val languages: Array<Language>
) :
    Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Country

        if (name != other.name) return false
        if (capital != other.capital) return false
        if (region != other.region) return false
        if (subregion != other.subregion) return false
        if (flag != other.flag) return false
        if (nativeName != other.nativeName) return false
        if (population != other.population) return false
        if (demonym != other.demonym) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + capital.hashCode()
        result = 31 * result + region.hashCode()
        result = 31 * result + subregion.hashCode()
        result = 31 * result + flag.hashCode()
        result = 31 * result + nativeName.hashCode()
        result = 31 * result + population
        result = 31 * result + demonym.hashCode()
        return result
    }


}
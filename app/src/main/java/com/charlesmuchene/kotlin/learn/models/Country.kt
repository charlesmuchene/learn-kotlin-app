package com.charlesmuchene.kotlin.learn.models

import android.os.Parcelable
import androidx.room.*
import com.charlesmuchene.kotlin.learn.db.converters.CurrencyArrayConverter
import com.charlesmuchene.kotlin.learn.db.converters.LanguageArrayConverter
import com.charlesmuchene.kotlin.learn.db.converters.StringArrayConverter
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Country class
 */
@Entity
@Parcelize
@TypeConverters(StringArrayConverter::class, CurrencyArrayConverter::class, LanguageArrayConverter::class)
data class Country(
    @SerializedName("name") @PrimaryKey val name: String,
    @SerializedName("capital") val capital: String,
    @SerializedName("region") val region: String,
    @SerializedName("subregion") val subregion: String,
    @SerializedName("flag") val flag: String,
    @SerializedName("nativeName") val nativeName: String,
    @SerializedName("population") val population: Int,
    @SerializedName("demonym") val demonym: String,
    @SerializedName("timezones") val timezones: Array<String>,
    @SerializedName("currencies") val currencies: Array<Currency>,
    @SerializedName("languages") val languages: Array<Language>
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
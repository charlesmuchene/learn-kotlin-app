package com.charlesmuchene.kotlin.learn.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

/**
 * Currency
 */
@Parcelize
@Serializable
data class Currency(@SerializedName("name") val name: String?, @SerializedName("symbol") val symbol: String?) :
    Parcelable {
    override fun toString(): String = "${name ?: "-"} (${symbol ?: "-"})"
}
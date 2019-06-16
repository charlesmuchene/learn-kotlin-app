package com.charlesmuchene.kotlin.learn.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

/**
 * Language
 */
@Parcelize
@Serializable
data class Language(
    @SerializedName("name") val name: String,
    @SerializedName("nativeName") val nativeName: String
) :
    Parcelable {
    override fun toString() = if (name == nativeName) name else "$name ($nativeName)"
}
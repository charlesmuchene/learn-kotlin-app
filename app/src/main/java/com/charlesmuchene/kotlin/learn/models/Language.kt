package com.charlesmuchene.kotlin.learn.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Language
 */
@Parcelize
data class Language(
    @SerializedName("name") val name: String,
    @SerializedName("nativeName") val nativeName: String
) :
    Parcelable {
    override fun toString() = if (name == nativeName) name else "$name ($nativeName)"
}
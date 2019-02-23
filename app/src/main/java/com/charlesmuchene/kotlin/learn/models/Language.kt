package com.charlesmuchene.kotlin.learn.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Language
 */
@Parcelize
data class Language(val name: String, val nativeName: String) : Parcelable {
    override fun toString() = if (name == nativeName) name else "$name ($nativeName)"
}
package com.charlesmuchene.kotlin.learn.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Currency
 */
@Parcelize
data class Currency(val name: String, val symbol: String) : Parcelable {
    override fun toString(): String = "$name ($symbol)"
}
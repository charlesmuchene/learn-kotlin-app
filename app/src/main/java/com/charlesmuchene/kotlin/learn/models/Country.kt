package com.charlesmuchene.kotlin.learn.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Country class
 */
@Parcelize
data class Country(val name: String, val capital: String, val region: String, val subregion: String, val flag: String) :
    Parcelable
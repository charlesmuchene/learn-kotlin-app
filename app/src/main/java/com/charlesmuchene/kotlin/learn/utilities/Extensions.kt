package com.charlesmuchene.kotlin.learn.utilities

import android.view.View
import com.charlesmuchene.kotlin.learn.models.Country

fun MutableList<Country>.promote() {
    find { it.name == MTAANI }?.let {
        remove(it); add(0, it)
    }
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

val String.orDash
    get() = if (this.isBlank()) "--" else this

val Country.stringifiedRegion: String
    get() = stringifyArray(arrayOf(region, subregion))
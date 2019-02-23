package com.charlesmuchene.kotlin.learn.utilities

import android.view.View
import com.charlesmuchene.kotlin.learn.models.Country

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}
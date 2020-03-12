package com.charlesmuchene.kotlin.learn

import android.app.Application
import com.charlesmuchene.kotlin.learn.utilities.Configuration

/**
 * App class
 */
@Suppress("unused")
class LearnApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Configuration.initialize(this)
    }

}
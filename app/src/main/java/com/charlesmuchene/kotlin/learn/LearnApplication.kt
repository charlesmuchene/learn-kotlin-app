package com.charlesmuchene.kotlin.learn

import android.app.Application
import com.charlesmuchene.kotlin.learn.utilities.Configuration

class LearnApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Configuration.initialize()
    }
}
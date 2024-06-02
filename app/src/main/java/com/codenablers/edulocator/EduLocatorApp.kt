package com.codenablers.edulocator

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EduLocatorApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
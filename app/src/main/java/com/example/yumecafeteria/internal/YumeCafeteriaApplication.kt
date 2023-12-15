package com.example.yumecafeteria.internal

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class YumeCafeteriaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@YumeCafeteriaApplication)
            modules(appModule)
        }
    }
}
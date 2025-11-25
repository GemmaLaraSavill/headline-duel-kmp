package dev.myhappyplace.headlineduelkmp

import android.app.Application
import dev.myhappyplace.headlineduelkmp.di.initKoin
import org.koin.android.ext.koin.androidContext

class HeadlineDuelApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@HeadlineDuelApplication)
        }
    }
}
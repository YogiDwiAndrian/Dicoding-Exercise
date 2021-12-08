package com.kajangdevs.netplay

import android.app.Application
import com.kajangdevs.netplay.di.applicationInjection
import com.kajangdevs.netplay.di.networkInjection
import com.kajangdevs.netplay.di.viewModelInjection
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NetPlay : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@NetPlay)
            modules(listOf(networkInjection, applicationInjection, viewModelInjection))
        }
    }
}
package com.client.loanapproval

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * The Base application class for the app
 */
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
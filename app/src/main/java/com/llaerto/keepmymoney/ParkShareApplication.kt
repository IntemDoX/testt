package com.llaerto.keepmymoney

import android.app.Application
import com.llaerto.keepmymoney.di.appModule
import com.llaerto.keepmymoney.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ParkShareApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ParkShareApplication)
            androidLogger()
            modules(
                appModule,
                viewModelModules)
        }
    }
}
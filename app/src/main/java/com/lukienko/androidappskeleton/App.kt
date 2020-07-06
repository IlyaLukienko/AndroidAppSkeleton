package com.lukienko.androidappskeleton

import android.app.Application
import com.lukienko.androidappskeleton.di.apiModule
import com.lukienko.androidappskeleton.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                listOf(
                    viewModelModule,
                    apiModule
                )
            )
        }
    }
}
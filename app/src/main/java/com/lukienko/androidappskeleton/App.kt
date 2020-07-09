package com.lukienko.androidappskeleton

import android.app.Application
import android.util.Log
import com.lukienko.androidappskeleton.di.*
import io.reactivex.plugins.RxJavaPlugins
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                listOf(
                    apiModule,
                    repositoryModule,
                    domainModule,
                    viewModelModule,
                    utilModule
                )
            )
        }

        RxJavaPlugins.setErrorHandler { throwable: Throwable? ->
            Log.e("RxError", throwable?.message.toString())
        }
    }
}
package com.lukienko.androidappskeleton.di

import com.lukienko.androidappskeleton.data.RetrofitProvider
import org.koin.dsl.module

val apiModule = module(override = true) {
    single { RetrofitProvider() }
}
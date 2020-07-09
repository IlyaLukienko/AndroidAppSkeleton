package com.lukienko.androidappskeleton.di

import com.lukienko.androidappskeleton.presentation.utils.ImageLoader
import org.koin.dsl.module

val utilModule = module(override = true) {
    single { ImageLoader() }
}
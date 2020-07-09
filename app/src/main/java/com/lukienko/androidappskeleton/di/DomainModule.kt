package com.lukienko.androidappskeleton.di

import com.lukienko.androidappskeleton.domain.interactor.CharacterInteractor
import org.koin.dsl.module

val domainModule = module(override = true) {
    single { CharacterInteractor() }
}
package com.lukienko.androidappskeleton.di

import com.lukienko.androidappskeleton.domain.useCase.GetCharactersUseCase
import org.koin.dsl.module

val useCaseModule = module(override = true) {
    single { GetCharactersUseCase() }
}